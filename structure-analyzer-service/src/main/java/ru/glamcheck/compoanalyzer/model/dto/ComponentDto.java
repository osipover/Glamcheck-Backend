package ru.glamcheck.compoanalyzer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ComponentDto {
    private String latinName;
    private Integer dangerFactor;
    private String naturalness;
    private List<CosmeticFeatureDto> cosmeticFeatures;
    private List<String> skinTypes;
}
