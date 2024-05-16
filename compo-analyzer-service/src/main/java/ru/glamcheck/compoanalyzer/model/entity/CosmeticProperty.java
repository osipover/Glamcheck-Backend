package ru.glamcheck.compoanalyzer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("cosmetic_property")
public class CosmeticProperty {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    public CosmeticProperty(String name) {
        this.name = name;
    }
}
