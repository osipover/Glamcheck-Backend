package ru.glamcheck.compoanalyzer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.glamcheck.compoanalyzer.controller.payload.StructurePayload;
import ru.glamcheck.compoanalyzer.service.StructureService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/composition")
@CrossOrigin(origins = "http://localhost:3000")
public class CompositionRestController {

    private final StructureService structureService;

    @GetMapping("analysis")
    public Mono<?> getCosmeticsAnalysis(@RequestBody StructurePayload structure) {
        return structureService.analizeStructure(structure);
    }
}
