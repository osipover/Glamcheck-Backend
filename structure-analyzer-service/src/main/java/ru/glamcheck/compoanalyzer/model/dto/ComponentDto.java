package ru.glamcheck.compoanalyzer.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ComponentDto {
    private String latinName;
    private Integer dangerFactor;
    private String naturalness;
    private List<CosmeticFeatureDto> cosmeticFeatures;
    private List<String> skinTypes;
}
