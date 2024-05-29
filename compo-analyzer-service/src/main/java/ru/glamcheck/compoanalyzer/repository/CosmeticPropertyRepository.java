package ru.glamcheck.compoanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glamcheck.compoanalyzer.model.entity.CosmeticProperty;

import java.util.Optional;

public interface CosmeticPropertyRepository extends JpaRepository<CosmeticProperty, Integer> {

    Optional<CosmeticProperty> findByName(String name);
}
