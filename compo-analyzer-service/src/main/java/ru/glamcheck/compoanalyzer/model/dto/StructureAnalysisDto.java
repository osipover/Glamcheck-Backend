package ru.glamcheck.compoanalyzer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class StructureAnalysisDto {
    private String naturalness;
    private double dangerFactor;
    private Map<String, Double> cosmeticFeatures;
    private Set<String> skinTypes;
}
