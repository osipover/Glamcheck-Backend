package ru.glamcheck.compoanalyzer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.glamcheck.compoanalyzer.model.entity.CosmeticProperty;

import java.util.Optional;

public interface CosmeticPropertyRepository extends MongoRepository<CosmeticProperty, String> {

    Optional<CosmeticProperty> findCosmeticPropertyByName(String name);
}
