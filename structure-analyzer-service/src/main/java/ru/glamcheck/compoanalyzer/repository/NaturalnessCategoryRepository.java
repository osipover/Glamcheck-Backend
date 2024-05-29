package ru.glamcheck.compoanalyzer.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.glamcheck.compoanalyzer.model.entity.NaturalnessCategory;

import java.util.Optional;

public interface NaturalnessCategoryRepository extends ReactiveCrudRepository<NaturalnessCategory, String> {

    Mono<NaturalnessCategory> findNaturalnessCategoryByTitle(String title);
}
