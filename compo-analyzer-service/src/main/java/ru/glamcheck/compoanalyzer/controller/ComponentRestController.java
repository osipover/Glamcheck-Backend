package ru.glamcheck.compoanalyzer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.glamcheck.compoanalyzer.model.dto.ComponentDto;
import ru.glamcheck.compoanalyzer.model.entity.Component;
import ru.glamcheck.compoanalyzer.service.ComponentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("component-api/component")
public class ComponentRestController {
    private final ComponentService componentService;

    @GetMapping("all")
    public ResponseEntity<List<ComponentDto>> findComponents() {
        List<ComponentDto> components = componentService.findAllComponents();
        return ResponseEntity.ok(components);
    }

    @GetMapping("{inciName}")
    public ResponseEntity<ComponentDto> findComponentByInciName(@PathVariable("inciName") String inciName) {
        ComponentDto component = componentService.findComponentByInciName(inciName);
        return ResponseEntity.ok(component);
        //todo: обработать ошибки
    }
}
