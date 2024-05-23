package ru.glamcheck.compoanalyzer.model.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.glamcheck.compoanalyzer.model.dto.CosmeticFeatureDto;
import ru.glamcheck.compoanalyzer.model.entity.Component;
import ru.glamcheck.compoanalyzer.model.entity.CosmeticFeature;
import ru.glamcheck.compoanalyzer.model.entity.CosmeticProperty;
import ru.glamcheck.compoanalyzer.repository.CosmeticFeatureRepository;
import ru.glamcheck.compoanalyzer.repository.CosmeticPropertyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CosmeticFeaturesConverter {

<<<<<<< HEAD
    private final CosmeticFeatureRepository cosmeticFeatureRepository;

    private final CosmeticPropertyRepository cosmeticPropertyRepository;

    public List<CosmeticFeature> convertCosmeticFeatureDtoToFeatures(List<CosmeticFeatureDto> cosmeticFeatureDtos, Component component) {
        return cosmeticFeatureDtos
                .stream()
                .map(dto -> new CosmeticFeature(
<<<<<<< HEAD
                        cosmeticPropertyRepository.findByName(dto.getProperty())
                                .orElseGet(() -> cosmeticPropertyRepository.save(new CosmeticProperty(dto.getProperty()))),
                        component,
=======
                        cosmeticPropertyRepository.findCosmeticPropertyByName(dto.getProperty())
                                .orElseGet(() -> cosmeticPropertyRepository.insert(new CosmeticProperty(dto.getProperty()))),
>>>>>>> d3cc878 (Мигрировал на mongodb)
                        dto.getValue()
                ))
                .toList();
    }
=======
//    private final CosmeticPropertyRepository cosmeticPropertyRepository;
//
//    public List<CosmeticFeature> convertCosmeticFeatureDtoToFeatures(List<CosmeticFeatureDto> cosmeticFeatureDtos, Component component) {
//        return cosmeticFeatureDtos
//                .stream()
//                .map(dto -> new CosmeticFeature(
//                        cosmeticPropertyRepository.findCosmeticPropertyByName(dto.getProperty())
//                                .switchIfEmpty(cosmeticPropertyRepository.save(new CosmeticProperty(dto.getProperty())))
//                                .block(),
//                        dto.getValue()
//                ))
//                .toList();
//    }
>>>>>>> d735fd6 (Реализовал реактивный поиск компонента с асинхронным добавлением компонента в базу)
}
