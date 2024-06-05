package ru.glamcheck.compoanalyzer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.glamcheck.compoanalyzer.model.dto.ComponentDto;
import ru.glamcheck.compoanalyzer.service.ComponentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/component")
public class ComponentRestController {
    private final ComponentService componentService;

    @GetMapping("{latinName}")
    public Mono<ComponentDto> findComponentByLatinName(@PathVariable("latinName") String latinName) {
        return componentService.findComponentByLatinName(latinName);
    }

}
