package ru.glamcheck.compoanalyzer.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.glamcheck.compoanalyzer.model.entity.Component;


public interface ComponentRepository extends ReactiveCrudRepository<Component, String> {

    Mono<Component> findComponentByInciNameIgnoreCase(String inciName);

}
