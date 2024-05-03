package ru.glamcheck.compoanalyzer.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "component", name = "t_skin_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SkinType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "skinTypes")
    private List<Component> components = new ArrayList<>();

    public SkinType(String name) {
        this.name = name;
    }
}
