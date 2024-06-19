package ru.glamcheck.compoanalyzer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.glamcheck.compoanalyzer.controller.payload.CompositionPayload;
import ru.glamcheck.compoanalyzer.model.dto.CompositionAnalysisDto;
import ru.glamcheck.compoanalyzer.service.CompositionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/composition")
@Tag(name="composition", description = "Работает с косметическим составом")
public class CompositionController {

    private final CompositionService structureService;

    @Operation(
            summary = "Анализ состава",
            description = "Позволяет по составу косметики получить анализ всего средства"
    )
    @GetMapping("analysis")
    public Mono<CompositionAnalysisDto> getCosmeticsAnalysis(@RequestBody CompositionPayload compositionPayload) {
        return structureService.analyzeComposition(compositionPayload.getComposition());
    }
}
