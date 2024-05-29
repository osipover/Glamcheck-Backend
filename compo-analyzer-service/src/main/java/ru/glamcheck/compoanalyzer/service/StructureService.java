package ru.glamcheck.compoanalyzer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.glamcheck.compoanalyzer.controller.payload.StructurePayload;
import ru.glamcheck.compoanalyzer.model.dto.ComponentDto;
import ru.glamcheck.compoanalyzer.model.dto.CosmeticFeatureDto;
import ru.glamcheck.compoanalyzer.model.dto.StructureAnalysisDto;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StructureService {

    private final ComponentService componentService;

    public Mono<?> analizeStructure(StructurePayload structurePayload) {
        String[] inciNames = extractInciNames(structurePayload);
        Flux<ComponentDto> components = Flux.fromArray(inciNames).flatMap(componentService::findComponentByInciName);
        return components.collectList().flatMap(componentsList -> {
                Mono<String> naturalessMono = Mono.fromCallable(() -> aggregateNaturaless(componentsList))
                        .subscribeOn(Schedulers.parallel());

                Mono<Double> dangerFactorAvgMono = Mono.fromCallable(() -> calcDangerFactorAvg(componentsList))
                .subscribeOn(Schedulers.parallel());

                Mono<List<CosmeticFeatureDto>> cosmeticFeaturesMono = Mono.fromCallable(() -> aggregateCosmeticFeatures(componentsList))
                .subscribeOn(Schedulers.parallel());

                Mono<Set<String>> skinTypesMono = Mono.fromCallable(() -> aggregateSkinTypes(componentsList))
                .subscribeOn(Schedulers.parallel());

                return Mono.zip(naturalessMono, dangerFactorAvgMono, cosmeticFeaturesMono, skinTypesMono)
                    .map(tuple -> {
                        String naturaless = tuple.getT1();
                        double dangerFactorAvg = tuple.getT2();
                        List<CosmeticFeatureDto> cosmeticFeatures = tuple.getT3();
                        Set<String> skinTypes = tuple.getT4();
                        return new StructureAnalysisDto(naturaless, dangerFactorAvg, cosmeticFeatures, skinTypes);
                    });
        });
    }

    private String[] extractInciNames(StructurePayload structurePayload) {
        return Arrays.stream(structurePayload.structure().split("\\s*[;,]\\s*"))
                .parallel()
                .map(String::trim)
                .map(String::toUpperCase)
                .distinct()
                .map(str -> str.replace(' ', '_'))
                .toArray(String[]::new);
    }

    private String aggregateNaturaless(List<ComponentDto> componentDtos) {
        return componentDtos.stream()
                .map(ComponentDto::getNaturalness)
                .anyMatch(str -> str.equals("Синтетический"))
                ? "Синтетический"
                : "Натуральный";
    }

    private double calcDangerFactorAvg(List<ComponentDto> componentDtos) {
        return componentDtos.stream()
                .mapToDouble(ComponentDto::getDangerFactor)
                .average()
                .orElse(0);
    }

    private List<CosmeticFeatureDto> aggregateCosmeticFeatures(List<ComponentDto> componentDtos) {
        Map<String, List<Double>> cosmeticFeaturesBuffer = new HashMap<>();
        componentDtos.forEach(componentDto ->
                componentDto.getCosmeticFeatures().forEach(cosmeticFeature ->
                        cosmeticFeaturesBuffer.computeIfAbsent(cosmeticFeature.getProperty(), k -> new ArrayList<>())
                                .add(cosmeticFeature.getValue())));
        List<CosmeticFeatureDto> cosmeticFeatures = new ArrayList<>();
        cosmeticFeaturesBuffer.forEach((key, values) -> {
            double averageValue = Math.floor(values.stream().mapToDouble(Double::doubleValue).average().orElse(0));
            cosmeticFeatures.add(new CosmeticFeatureDto(key, averageValue));
        });
        return cosmeticFeatures;
    }

    private Set<String> aggregateSkinTypes(List<ComponentDto> componentDtos) {
        return componentDtos.stream()
                .map(ComponentDto::getSkinTypes)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }
}
