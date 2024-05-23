package ru.glamcheck.compoanalyzer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.glamcheck.compoanalyzer.controller.payload.StructurePayload;

@Service
@RequiredArgsConstructor
public class StructureService {

    private final ComponentService componentService;

//    public Mono<Void> analizeStructure(StructurePayload structurePayload) {
//        String[] components = structurePayload.structure().split("\\s*[;,]\\s*");
//        Flux.just(components)
//                .flatMap(componentService::findComponentByInciName)
//                .
//    }
}
