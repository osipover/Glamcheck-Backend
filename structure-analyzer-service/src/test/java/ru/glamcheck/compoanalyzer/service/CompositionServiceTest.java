package ru.glamcheck.compoanalyzer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.glamcheck.compoanalyzer.client.exception.ComponentNotFoundException;
import ru.glamcheck.compoanalyzer.model.dto.ComponentDto;
import ru.glamcheck.compoanalyzer.model.dto.CompositionAnalysisDto;
import ru.glamcheck.compoanalyzer.model.dto.CosmeticFeatureDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CompositionServiceTest {

    @Mock
    ComponentService componentService;

    @InjectMocks
    CompositionService compositionService;

    @Test
    void analyzeComposition_ComponentsExists_returnsAnalysisDto() {
        ComponentDto component1 = new ComponentDto(
                "GLYCINE",
                8,
                "Синтетический",
                List.of(new CosmeticFeatureDto("Смягчение кожи", 10.0), new CosmeticFeatureDto("Увлажнение кожи", 8.0)),
                List.of("Детская кожа", "Для губ", "Для кожи тела")
        );
        ComponentDto component2 = new ComponentDto(
                "AQUA",
                0,
                "Натуральный",
                List.of(new CosmeticFeatureDto("Смягчение кожи", 4.0), new CosmeticFeatureDto("Укрепление кожи", 6.0)),
                List.of("Детская кожа", "Взрослая кожа", "Для кожи тела")
        );
        CompositionAnalysisDto expected = new CompositionAnalysisDto(
                "Синтетический",
                4,
                List.of(new CosmeticFeatureDto("Укрепление кожи", 6.0), new CosmeticFeatureDto( "Смягчение кожи", 7.0), new CosmeticFeatureDto("Увлажнение кожи", 8.0)),
                Set.of("Детская кожа", "Для губ", "Для кожи тела", "Взрослая кожа"),
                new ArrayList<>()
        );

        when(componentService.findComponentByLatinName("GLYCINE"))
                .thenReturn(Mono.just(component1));
        when(componentService.findComponentByLatinName("AQUA"))
                .thenReturn(Mono.just(component2));


        StepVerifier.create(compositionService.analyzeComposition("aqua, glycine"))
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    void analyzeComposition_OneComponentDoesNotExist_returnsAnalysisDto() {
        ComponentDto componentDto = new ComponentDto(
                "GLYCINE",
                8,
                "Синтетический",
                List.of(new CosmeticFeatureDto("Смягчение кожи", 10.0), new CosmeticFeatureDto("Увлажнение кожи", 8.0)),
                List.of("Детская кожа", "Для губ", "Для кожи тела")
        );
        CompositionAnalysisDto expected = new CompositionAnalysisDto(
                "Синтетический",
                8,
                List.of(new CosmeticFeatureDto("Смягчение кожи", 10.0), new CosmeticFeatureDto("Увлажнение кожи", 8.0)),
                Set.of("Детская кожа", "Для губ", "Для кожи тела"),
//                new ArrayList<>()
                List.of("UNREAL COMPONENT")
        );
        when(componentService.findComponentByLatinName("UNREAL COMPONENT"))
                .thenThrow(new ComponentNotFoundException("UNREAL COMPONENT"));

        when(componentService.findComponentByLatinName("GLYCINE"))
                .thenReturn(Mono.just(componentDto));


        StepVerifier.create(compositionService.analyzeComposition("unreal component, glycine"))
                .expectNext(expected)
                .verifyComplete();
    }
}

