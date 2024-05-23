package ru.glamcheck.compoanalyzer.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.glamcheck.compoanalyzer.client.ComponentClient;
import ru.glamcheck.compoanalyzer.client.response.ComponentResponse;
import ru.glamcheck.compoanalyzer.model.dto.ComponentDto;
import ru.glamcheck.compoanalyzer.model.entity.Component;
import ru.glamcheck.compoanalyzer.model.mapper.ComponentDtoMapper;
import ru.glamcheck.compoanalyzer.model.mapper.ComponentFromResponseMapper;
import ru.glamcheck.compoanalyzer.repository.ComponentRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ComponentService {

    private final ComponentRepository componentRepository;

    private final ComponentClient componentClient;

    private final ComponentDtoMapper componentDtoMapper;

    private final ComponentFromResponseMapper componentFromResponseMapper;

    public Mono<ComponentDto> findComponentByInciName(String inciName) {
        return componentRepository.findComponentByInciNameIgnoreCase(inciName)
                .switchIfEmpty(componentClient.getComponentByInciName(inciName)
                        .flatMap(componentResponse -> {
                            Component component = componentFromResponseMapper.apply(componentResponse);
                            componentRepository.save(component)
                                    .subscribeOn(Schedulers.boundedElastic())
                                    .subscribe();
                            return Mono.just(component);
                        }))
                .map(componentDtoMapper);
    }
}
