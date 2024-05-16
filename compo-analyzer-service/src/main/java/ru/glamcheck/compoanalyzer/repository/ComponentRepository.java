package ru.glamcheck.compoanalyzer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.glamcheck.compoanalyzer.model.entity.Component;

import java.util.Optional;

public interface ComponentRepository extends MongoRepository<Component, String> {

    Optional<Component> findComponentByInciName(String inciName);

}
