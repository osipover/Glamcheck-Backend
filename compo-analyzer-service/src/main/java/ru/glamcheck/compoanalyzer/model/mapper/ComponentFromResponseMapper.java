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

    private final CosmeticFeaturesConverter cosmeticFeaturesConverter;

    private final SkinTypesConverter skinTypesConverter;

    private final NaturalnessCategoryConverter naturalnessCategoryConverter;

    @Override
    public Component apply(ComponentResponse response) {
        Component component = new Component();
        component.setInciName(response.getInciName());
        component.setDangerFactor(response.getDangerFactor());

        NaturalnessCategory naturalness = naturalnessCategoryConverter.convertNaturalnessNameToCategory(response.getNaturalness());
        component.setNaturalness(naturalness);

        List<CosmeticFeature> features = cosmeticFeaturesConverter.convertCosmeticFeatureDtoToFeatures(
                response.getCosmeticFeatures(),
                component);
        component.setCosmeticFeatures(features);

        List<SkinType> skinTypes = skinTypesConverter.convertSkinNamesToSkinTypes(response.getSkinTypes());
        component.setSkinTypes(skinTypes);

        return component;
    }
}
