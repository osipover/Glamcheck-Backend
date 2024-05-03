package ru.glamcheck.compoanalyzer.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "component", name = "t_cosmetic_feature")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CosmeticFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cosmetic_property_id")
    private CosmeticProperty cosmeticProperty;

    @Column(name = "c_value")
    private Integer value;
}
