package ru.glamcheck.compoanalyzer.client;

import reactor.core.publisher.Mono;
import ru.glamcheck.compoanalyzer.client.exception.ComponentNotFoundException;
import ru.glamcheck.compoanalyzer.client.response.ComponentResponse;

public interface ComponentClient {

    Mono<ComponentResponse> getComponentByLatinName(String latinName) throws ComponentNotFoundException;
}
