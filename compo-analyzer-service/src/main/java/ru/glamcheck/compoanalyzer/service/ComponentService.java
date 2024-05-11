package ru.glamcheck.compoanalyzer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.glamcheck.compoanalyzer.model.dto.ComponentDto;
import ru.glamcheck.compoanalyzer.model.entity.Component;
import ru.glamcheck.compoanalyzer.model.mapper.ComponentDtoMapper;
import ru.glamcheck.compoanalyzer.repository.ComponentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComponentService {
    private final ComponentRepository componentRepository;
    private final ComponentDtoMapper componentDtoMapper;

    public List<ComponentDto> findAllComponents() {
            return componentRepository.findAll()
                    .stream()
                    .map(componentDtoMapper)
                    .toList();
    }

}
