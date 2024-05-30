package ru.glamcheck.compoanalyzer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.glamcheck.compoanalyzer.client.ComponentClient;
import ru.glamcheck.compoanalyzer.client.exception.ComponentNotFoundException;
import ru.glamcheck.compoanalyzer.model.dto.ComponentDto;
import ru.glamcheck.compoanalyzer.model.entity.Component;
import ru.glamcheck.compoanalyzer.model.mapper.ComponentDtoMapper;
import ru.glamcheck.compoanalyzer.model.mapper.ComponentFromResponseMapper;
import ru.glamcheck.compoanalyzer.repository.ComponentRepository;

@Service
@AllArgsConstructor
public class ComponentService {

    private final ComponentRepository componentRepository;

    private final ComponentClient componentClient;

    private final ComponentDtoMapper componentDtoMapper;

    private final ComponentFromResponseMapper componentFromResponseMapper;

    public Mono<ComponentDto> findComponentByLatinName(String latinName) throws ComponentNotFoundException {
        return componentRepository.findFirstByLatinNameIgnoreCase(latinName)
                .switchIfEmpty(Mono.defer(() -> componentClient.getComponentByLatinName(latinName)
                        .flatMap(componentResponse -> {
                            Component component = componentFromResponseMapper.apply(componentResponse);
                            componentRepository.save(component)
                                    .subscribeOn(Schedulers.boundedElastic())
                                    .subscribe();
                            return Mono.just(component);
                        })))
                .map(componentDtoMapper);
    }
}
