package ru.glamcheck.compoanalyzer.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public List<ComponentDto> findAllComponents() {
            return componentRepository.findAll()
                    .stream()
                    .map(componentDtoMapper)
                    .toList();
    }

    @Transactional
    public ComponentDto findComponentByInciName(String inciName) {
        Optional<Component> componentOptional = componentRepository.findComponentByInciName(inciName);
        if (componentOptional.isPresent()) {
            return componentDtoMapper.apply(componentOptional.get());
        }
        ComponentResponse componentResponse = componentClient.getComponentByInciName(inciName);
        Component component = componentFromResponseMapper.apply(componentResponse);
        componentRepository.save(component);
        return componentDtoMapper.apply(component);
    }
}
