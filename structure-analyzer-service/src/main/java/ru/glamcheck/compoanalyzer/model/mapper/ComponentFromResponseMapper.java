package ru.glamcheck.compoanalyzer.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.glamcheck.compoanalyzer.client.response.ComponentResponse;
import ru.glamcheck.compoanalyzer.model.converter.CosmeticFeaturesConverter;
import ru.glamcheck.compoanalyzer.model.converter.NaturalnessCategoryConverter;
import ru.glamcheck.compoanalyzer.model.converter.SkinTypesConverter;
import ru.glamcheck.compoanalyzer.model.entity.*;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ComponentFromResponseMapper implements Function<ComponentResponse, Component> {

    @Override
    public Component apply(ComponentResponse response) {
        return Component.builder()
                .latinName(response.getInciName())
                .naturalness(response.getNaturalness())
                .dangerFactor(response.getDangerFactor())
                .cosmeticFeatures(response.getCosmeticFeatures().stream()
                        .map(feature -> new CosmeticFeature(feature.getProperty(), feature.getValue()))
                        .toList())
                .skinTypes(response.getSkinTypes())
                .build();
    }
}
