package ru.glamcheck.compoanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glamcheck.compoanalyzer.model.entity.SkinType;

import java.util.Optional;

public interface SkinTypeRepository extends JpaRepository<SkinType, Integer> {

    Optional<SkinType> findByName(String name);
}
