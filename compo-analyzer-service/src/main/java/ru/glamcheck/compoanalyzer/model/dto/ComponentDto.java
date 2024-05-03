package ru.glamcheck.compoanalyzer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.glamcheck.compoanalyzer.model.entity.CosmeticFeature;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentDto {
    private Integer id;
    private String inciName;
    private Integer dangerFactor;
    private String naturalness;
    private List<CosmeticFeatureDto> cosmeticFeatures;
    private List<SkinTypeDto> skinTypes;
}
