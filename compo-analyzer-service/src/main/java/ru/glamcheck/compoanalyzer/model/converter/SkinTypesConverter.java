package ru.glamcheck.compoanalyzer.model.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.glamcheck.compoanalyzer.model.entity.SkinType;
import ru.glamcheck.compoanalyzer.repository.SkinTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkinTypesConverter {

    private final SkinTypeRepository skinTypeRepository;

    public List<SkinType> convertSkinNamesToSkinTypes(List<String> skinNames) {
        return skinNames
                .stream()
                .map(name ->
                        skinTypeRepository.findByName(name)
                                .orElseGet(() -> skinTypeRepository.save(new SkinType(name)))
                )
                .toList();
    }
}
