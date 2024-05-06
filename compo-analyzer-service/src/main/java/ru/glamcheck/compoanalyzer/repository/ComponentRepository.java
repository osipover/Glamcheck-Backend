package ru.glamcheck.compoanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glamcheck.compoanalyzer.model.entity.Component;

import java.util.Optional;

public interface ComponentRepository extends JpaRepository<Component, Integer> {

    Optional<Component> findByInciName(String inciName);
}
