package ru.glamcheck.compoanalyzer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Document("component")
public class Component {

    @Id
    private String id;

    @Indexed(unique = true)
    private String inciName;

    private Integer dangerFactor;

    @DBRef
    private NaturalnessCategory naturalness;

    private List<CosmeticFeature> cosmeticFeatures = new ArrayList<>();

    @DBRef
    private List<SkinType> skinTypes = new ArrayList<>();
}
