package ru.glamcheck.compoanalyzer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.glamcheck.compoanalyzer.client.exception.ComponentNotFoundException;
import ru.glamcheck.compoanalyzer.model.dto.ComponentDto;
import ru.glamcheck.compoanalyzer.model.dto.CosmeticFeatureDto;
import ru.glamcheck.compoanalyzer.model.dto.CompositionAnalysisDto;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompositionService {

    private final ComponentService componentService;

    public Mono<CompositionAnalysisDto> analyzeComposition(String composition) {
        String[] latinNames = extractLatinNames(composition);
        List<String> notFoundComponents = new ArrayList<>();
        Flux<ComponentDto> components = Flux.fromArray(latinNames)
                .flatMap(componentService::findComponentByLatinName)
                .onErrorResume(throwable -> {
                    if (throwable instanceof ComponentNotFoundException notFoundException) {
                        notFoundComponents.add(notFoundException.getComponentName());
                    }
                    return Flux.empty();
                });
        return aggregateComponentsToStructureAnalysis(components, notFoundComponents);
    }

    private Mono<CompositionAnalysisDto> aggregateComponentsToStructureAnalysis(Flux<ComponentDto> components, List<String> notFoundComponents) {
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
                        String naturalness = tuple.getT1();
                        double dangerFactorAvg = tuple.getT2();
                        List<CosmeticFeatureDto> cosmeticFeatures = tuple.getT3();
                        Set<String> skinTypes = tuple.getT4();
                        return new CompositionAnalysisDto(naturalness, dangerFactorAvg, cosmeticFeatures, skinTypes, notFoundComponents);
                    });
        });
    }

    private String[] extractLatinNames(String composition) {
        return Arrays.stream(composition.split("\\s*[;,]\\s*"))
                .parallel()
                .map(String::trim)
                .map(String::toUpperCase)
                .distinct()
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
