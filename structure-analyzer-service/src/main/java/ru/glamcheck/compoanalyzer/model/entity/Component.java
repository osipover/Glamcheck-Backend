package ru.glamcheck.compoanalyzer.model.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("component")
public class Component {

    @Id
    private String id;

    @Indexed(unique = true)
    private String latinName;

    private Integer dangerFactor;

    private String naturalness;

    private List<CosmeticFeature> cosmeticFeatures = new ArrayList<>();

    private List<String> skinTypes = new ArrayList<>();
}
