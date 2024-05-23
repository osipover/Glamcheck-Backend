package ru.glamcheck.compoanalyzer.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.glamcheck.compoanalyzer.model.entity.SkinType;

import java.util.Optional;

public interface SkinTypeRepository extends ReactiveCrudRepository<SkinType, String> {

    Mono<SkinType> findSkinTypeByName(String name);
}
