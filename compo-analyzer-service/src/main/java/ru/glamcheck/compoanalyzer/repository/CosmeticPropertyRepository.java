package ru.glamcheck.compoanalyzer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.glamcheck.compoanalyzer.model.entity.CosmeticProperty;

import java.util.Optional;

public interface CosmeticPropertyRepository extends ReactiveCrudRepository<CosmeticProperty, String> {

    Mono<CosmeticProperty> findCosmeticPropertyByName(String name);
}
