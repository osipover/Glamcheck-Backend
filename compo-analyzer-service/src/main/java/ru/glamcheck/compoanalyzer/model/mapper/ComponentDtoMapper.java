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
    private final SkinTypeDtoMapper skinTypeDtoMapper;

    @Override
    public ComponentDto apply(Component component) {
        return new ComponentDto(
                component.getId(),
                component.getInciName(),
                component.getDangerFactor(),
                component.getNaturalness().getName(),
                component.getCosmeticFeatures()
                        .stream()
                        .map(cosmeticFeatureDtoMapper)
                        .toList(),
                component.getSkinTypes()
                        .stream()
                        .map(skinTypeDtoMapper)
                        .toList()
        );
    }
}
