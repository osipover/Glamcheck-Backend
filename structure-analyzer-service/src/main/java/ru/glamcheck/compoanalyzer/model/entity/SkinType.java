package ru.glamcheck.compoanalyzer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document("skin_type")
public class SkinType {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    public SkinType(String name) {
        this.name = name;
    }
}
