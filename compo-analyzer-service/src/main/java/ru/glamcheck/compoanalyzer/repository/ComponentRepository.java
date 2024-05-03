package ru.glamcheck.compoanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glamcheck.compoanalyzer.model.entity.Component;

public interface ComponentRepository extends JpaRepository<Component, Integer> {

}
