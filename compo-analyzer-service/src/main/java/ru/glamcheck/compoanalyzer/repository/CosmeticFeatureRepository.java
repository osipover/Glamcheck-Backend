package ru.glamcheck.compoanalyzer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.glamcheck.compoanalyzer.model.entity.CosmeticFeature;

public interface CosmeticFeatureRepository extends ReactiveCrudRepository<CosmeticFeature, String> {

}
