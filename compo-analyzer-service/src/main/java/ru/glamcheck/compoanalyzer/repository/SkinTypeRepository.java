package ru.glamcheck.compoanalyzer.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.glamcheck.compoanalyzer.model.entity.SkinType;

import java.util.Optional;

public interface SkinTypeRepository extends MongoRepository<SkinType, String> {

    Optional<SkinType> findSkinTypeByName(String name);
}
