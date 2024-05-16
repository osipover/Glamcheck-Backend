package ru.glamcheck.compoanalyzer.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.glamcheck.compoanalyzer.model.entity.NaturalnessCategory;

import java.util.Optional;


public interface NaturalnessCategoryRepository extends MongoRepository<NaturalnessCategory, String> {

    Optional<NaturalnessCategory> findNaturalnessCategoryByTitle(String title);
}
