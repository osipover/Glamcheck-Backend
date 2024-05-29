package ru.glamcheck.compoanalyzer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.glamcheck.compoanalyzer.controller.payload.StructurePayload;
import ru.glamcheck.compoanalyzer.model.dto.ComponentDto;
import ru.glamcheck.compoanalyzer.model.dto.StructureAnalysisDto;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StructureService {

    private final ComponentService componentService;

    public Mono<?> analizeStructure(StructurePayload structurePayload) {
        String[] inciNames = structurePayload.structure().split("\\s*[;,]\\s*");
        Flux<ComponentDto> components = Flux.fromArray(inciNames).flatMap(componentService::findComponentByInciName);
        Mono<StructureAnalysisDto> structureAnalysisDto = components.collectList().map(componentsList -> {
            String naturaless = aggregateNaturalness(componentsList);
            double dangerFactorAvg = calcDangerFactorAvg(componentsList);
            Map<String, Double> cosmeticFeatures = aggregateCosmeticFeatures(componentsList);
            Set<String> skinTypes = aggregateSkinTypes(componentsList);
            return new StructureAnalysisDto(naturaless, dangerFactorAvg, cosmeticFeatures, skinTypes);
        });
        return structureAnalysisDto;
    }

    private String aggregateNaturalness(List<ComponentDto> componentDtos) {
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

    private Map<String, Double> aggregateCosmeticFeatures(List<ComponentDto> componentDtos) {
        Map<String, List<Integer>> cosmeticFeaturesBuffer = new HashMap<>();
        componentDtos.forEach(componentDto ->
                componentDto.getCosmeticFeatures().forEach(cosmeticFeature ->
                        cosmeticFeaturesBuffer.computeIfAbsent(cosmeticFeature.getProperty(), k -> new ArrayList<>())
                                .add(cosmeticFeature.getValue())));
        Map<String, Double> cosmeticFeatures = new HashMap<>();
        cosmeticFeaturesBuffer.forEach((key, values) -> {
            double averageValue = Math.floor(values.stream().mapToInt(Integer::intValue).average().orElse(0));
            cosmeticFeatures.put(key, averageValue);
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
