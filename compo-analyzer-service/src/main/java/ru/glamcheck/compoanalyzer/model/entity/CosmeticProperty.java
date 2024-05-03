package ru.glamcheck.compoanalyzer.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "component", name = "t_cosmetic_property")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CosmeticProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cosmeticProperty")
    private List<CosmeticFeature> cosmeticFeatures = new ArrayList<>();
}
