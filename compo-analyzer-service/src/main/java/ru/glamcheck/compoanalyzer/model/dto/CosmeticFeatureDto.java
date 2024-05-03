package ru.glamcheck.compoanalyzer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CosmeticFeatureDto {
    private String property;
    private Integer value;
}
