package ru.glamcheck.compoanalyzer.model.mapper;

import org.springframework.stereotype.Service;
import ru.glamcheck.compoanalyzer.model.dto.CosmeticFeatureDto;
import ru.glamcheck.compoanalyzer.model.entity.CosmeticFeature;

import java.util.function.Function;

@Service
public class CosmeticFeatureDtoMapper implements Function<CosmeticFeature, CosmeticFeatureDto>  {
    @Override
    public CosmeticFeatureDto apply(CosmeticFeature cosmeticFeature) {
        return new CosmeticFeatureDto(
                cosmeticFeature.getCosmeticProperty().getName(),
                cosmeticFeature.getValue()
        );
    }
}
