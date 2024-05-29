package ru.glamcheck.compoanalyzer.client.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.glamcheck.compoanalyzer.model.dto.CosmeticFeatureDto;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ComponentResponse {

    private String inciName;

    private Integer dangerFactor;

    private String naturalness;

    private List<CosmeticFeatureDto> cosmeticFeatures;

    private List<String> skinTypes;
}
