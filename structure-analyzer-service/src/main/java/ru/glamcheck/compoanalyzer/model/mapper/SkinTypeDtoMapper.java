package ru.glamcheck.compoanalyzer.model.mapper;

import org.springframework.stereotype.Service;
import ru.glamcheck.compoanalyzer.model.dto.SkinTypeDto;
import ru.glamcheck.compoanalyzer.model.entity.SkinType;

import java.util.function.Function;

@Service
public class SkinTypeDtoMapper implements Function<SkinType, SkinTypeDto> {
    @Override
    public SkinTypeDto apply(SkinType skinType) {
        return new SkinTypeDto(skinType.getName());
    }
}
