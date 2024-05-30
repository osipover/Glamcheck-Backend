package ru.glamcheck.compoanalyzer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.glamcheck.compoanalyzer.client.ComponentClient;
import ru.glamcheck.compoanalyzer.client.response.ComponentResponse;
import ru.glamcheck.compoanalyzer.model.dto.ComponentDto;
import ru.glamcheck.compoanalyzer.model.dto.CosmeticFeatureDto;
import ru.glamcheck.compoanalyzer.model.entity.Component;
import ru.glamcheck.compoanalyzer.model.entity.CosmeticFeature;
import ru.glamcheck.compoanalyzer.model.mapper.ComponentDtoMapper;
import ru.glamcheck.compoanalyzer.model.mapper.ComponentFromResponseMapper;
import ru.glamcheck.compoanalyzer.repository.ComponentRepository;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ComponentServiceTest {

    @Mock
    ComponentRepository componentRepository;

    @Mock
    ComponentClient componentClient;

    @Mock
    ComponentDtoMapper componentDtoMapper;

    @Mock
    ComponentFromResponseMapper componentFromResponseMapper;

    @InjectMocks
    ComponentService componentService;

    @Test
    void findComponentByLatinName_ComponentDoesNotExistInRepository_ReturnsComponentDtoFromClient() {
        // Arrange
        String latinName = "GLYCINE";
        ComponentResponse componentResponse = new ComponentResponse(
                "GLYCINE",
                8,
                "Синтетический",
                List.of(new CosmeticFeatureDto("Смягчение кожи", 10.0), new CosmeticFeatureDto("Увлажнение кожи", 8.0)),
                List.of("Детская кожа", "Для губ", "Для кожи тела")
        );
        ComponentDto expectedDto = new ComponentDto(
                "GLYCINE",
                8,
                "Синтетический",
                List.of(new CosmeticFeatureDto("Смягчение кожи", 10.0), new CosmeticFeatureDto("Увлажнение кожи", 8.0)),
                List.of("Детская кожа", "Для губ", "Для кожи тела")
        );
        Component savedComponent = Component.builder()
                .latinName("GLYCINE")
                .dangerFactor(8)
                .naturalness("Синтетический")
                .cosmeticFeatures(List.of(new CosmeticFeature("Смягчение кожи", 10.0), new CosmeticFeature("Увлажнение кожи", 8.0)))
                .skinTypes(List.of("Детская кожа", "Для губ", "Для кожи тела"))
                .build();
        when(componentRepository.findFirstByLatinNameIgnoreCase(latinName)).thenReturn(Mono.empty());
        when(componentClient.getComponentByLatinName(latinName)).thenReturn(Mono.just(componentResponse));
        when(componentFromResponseMapper.apply(componentResponse)).thenReturn(savedComponent);
        when(componentRepository.save(savedComponent)).thenReturn(Mono.just(savedComponent));
        when(componentDtoMapper.apply(savedComponent)).thenReturn(expectedDto);
        // Act
        Mono<ComponentDto> resultMono = componentService.findComponentByLatinName(latinName);

        // Assert
        StepVerifier.create(resultMono)
                .expectNext(expectedDto)
                .verifyComplete();
    }

    @Test
    void findComponentByLatinName_ComponentExistsInRepository_ReturnsComponentDtoFromRepository() {
        // Arrange
        String latinName = "GLYCINE";
        Component component = Component.builder()
                .latinName("GLYCINE")
                .dangerFactor(8)
                .naturalness("Синтетический")
                .cosmeticFeatures(List.of(new CosmeticFeature("Смягчение кожи", 10.0), new CosmeticFeature("Увлажнение кожи", 8.0)))
                .skinTypes(List.of("Детская кожа", "Для губ", "Для кожи тела"))
                .build();
        ComponentDto expectedDto = new ComponentDto(
                "GLYCINE",
                8,
                "Синтетический",
                List.of(new CosmeticFeatureDto("Смягчение кожи", 10.0), new CosmeticFeatureDto("Увлажнение кожи", 8.0)),
                List.of("Детская кожа", "Для губ", "Для кожи тела")
        );

        doReturn(Mono.just(component)).when(this.componentRepository).findFirstByLatinNameIgnoreCase(latinName);
        doReturn(expectedDto).when(this.componentDtoMapper).apply(component);

        // Act
        StepVerifier.create(componentService.findComponentByLatinName(latinName))
                // Assert
                .expectNext(expectedDto)
                .verifyComplete();

        verify(componentRepository).findFirstByLatinNameIgnoreCase(latinName);
        verify(componentClient, never()).getComponentByLatinName(anyString());
    }
}