package ru.glamcheck.compoanalyzer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.glamcheck.compoanalyzer.controller.payload.CompositionPayload;
import ru.glamcheck.compoanalyzer.model.dto.CompositionAnalysisDto;
import ru.glamcheck.compoanalyzer.service.CompositionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/composition")
@CrossOrigin(origins = "http://localhost:3000")
public class CompositionRestController {

    private final CompositionService structureService;

    @GetMapping("analysis")
    public Mono<CompositionAnalysisDto> getCosmeticsAnalysis(@RequestBody CompositionPayload compositionPayload) {
        return structureService.analyzeComposition(compositionPayload.structure());
    }
}
