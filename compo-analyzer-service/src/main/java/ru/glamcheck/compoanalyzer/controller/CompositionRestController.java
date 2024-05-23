package ru.glamcheck.compoanalyzer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.glamcheck.compoanalyzer.controller.payload.StructurePayload;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/composition")
public class CompositionRestController {

//    @GetMapping("analysis")
//    public Mono<Void> getCosmeticsAnalysis(@RequestBody StructurePayload structure) {
//
//    }
}
