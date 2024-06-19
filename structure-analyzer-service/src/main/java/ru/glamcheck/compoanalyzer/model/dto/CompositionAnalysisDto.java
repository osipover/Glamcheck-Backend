package ru.glamcheck.compoanalyzer.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CompositionAnalysisDto {
    @Schema(description = "Натуральность", example = "Синтетический")
    private String naturalness;

    @Schema(description = "Фактор опасности", example = "0.0")
    private double dangerFactor;

    @Schema(
            description = "Косметические свойства"

    )
    private List<CosmeticFeatureDto> cosmeticFeatures;

    @Schema(
            description = "Типы кожи, к которым подходит средство",
            type = "array",
            example = "[\"Для кожи тела\", \"Для губ\"]")
    private Set<String> skinTypes;

    @Schema(
            description = "Компоненты состава, найти которые не удалось",
            type = "array",
            example = "[\"CHAMOMILLA RECUTITA FLOWER EXTRACT\"]"
    )
    private List<String> notFoundComponents;
}
