package ru.glamcheck.compoanalyzer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CosmeticFeature {

    @DBRef
    private CosmeticProperty cosmeticProperty;

    private Integer value;

}
