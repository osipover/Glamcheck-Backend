package ru.glamcheck.compoanalyzer.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "component", name = "t_component")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_inci_name")
    private String inciName;

    @Column(name = "c_danger_factor")
    private Integer dangerFactor;

    @OneToOne
    @JoinColumn(name = "naturalness_id")
    private NaturalnessCategory naturalness;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.REMOVE
    }, fetch = FetchType.EAGER)
    @JoinColumn(name = "component_id")
    private List<CosmeticFeature> cosmeticFeatures = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST
    }, fetch = FetchType.EAGER)
    @JoinTable(
            schema = "component",
            name = "t_skin_type_component",
            joinColumns = @JoinColumn(name = "component_id"),
            inverseJoinColumns = @JoinColumn(name = "skin_type_id")
    )
    private List<SkinType> skinTypes = new ArrayList<>();
}
