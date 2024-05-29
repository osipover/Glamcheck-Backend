package ru.glamcheck.compoanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glamcheck.compoanalyzer.model.entity.CosmeticFeature;

public interface CosmeticFeatureRepository extends JpaRepository<CosmeticFeature, Integer> {

}
