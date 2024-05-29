package ru.glamcheck.compoanalyzer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document("naturalness_category")
public class NaturalnessCategory {

    @Id
    private String id;

    @Indexed(unique = true)
    private String title;

    public NaturalnessCategory(String title) {
        this.title = title;
    }
}
