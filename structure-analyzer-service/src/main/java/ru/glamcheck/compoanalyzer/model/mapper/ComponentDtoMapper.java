package ru.glamcheck.compoanalyzer.model.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.glamcheck.compoanalyzer.model.dto.ComponentDto;
import ru.glamcheck.compoanalyzer.model.entity.Component;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ComponentDtoMapper implements Function<Component, ComponentDto> {
    private final CosmeticFeatureDtoMapper cosmeticFeatureDtoMapper;

    @Override
    public ComponentDto apply(Component component) {
        return ComponentDto.builder()
                .latinName(component.getLatinName())
                .dangerFactor(component.getDangerFactor())
                .naturalness(component.getNaturalness())
                .cosmeticFeatures(component.getCosmeticFeatures().stream()
                        .map(cosmeticFeatureDtoMapper)
                        .toList())
                .skinTypes(component.getSkinTypes())
                .build();
    }
}
