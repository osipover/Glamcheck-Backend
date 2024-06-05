package ru.glamcheck.compoanalyzer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class CompositionAnalysisDto {
    private String naturalness;
    private double dangerFactor;
    private List<CosmeticFeatureDto> cosmeticFeatures;
    private Set<String> skinTypes;
    private List<String> notFoundComponents;
}
