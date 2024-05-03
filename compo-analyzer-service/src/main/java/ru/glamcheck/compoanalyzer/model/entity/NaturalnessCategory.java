package ru.glamcheck.compoanalyzer.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "component", name = "t_naturalness_category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NaturalnessCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_name")
    private String name;
}
