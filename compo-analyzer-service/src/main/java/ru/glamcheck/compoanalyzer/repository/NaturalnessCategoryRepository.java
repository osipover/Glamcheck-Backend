package ru.glamcheck.compoanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glamcheck.compoanalyzer.model.entity.NaturalnessCategory;

import java.util.Optional;

public interface NaturalnessCategoryRepository extends JpaRepository<NaturalnessCategory, Integer> {

    Optional<NaturalnessCategory> findByName(String name);
}
