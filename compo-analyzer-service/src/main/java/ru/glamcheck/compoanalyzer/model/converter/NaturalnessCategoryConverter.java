package ru.glamcheck.compoanalyzer.model.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.glamcheck.compoanalyzer.model.entity.NaturalnessCategory;
import ru.glamcheck.compoanalyzer.repository.NaturalnessCategoryRepository;

@Component
@RequiredArgsConstructor
public class NaturalnessCategoryConverter {

    private final NaturalnessCategoryRepository naturalnessCategoryRepository;

    public NaturalnessCategory convertNaturalnessNameToCategory(String naturalnessName) {
<<<<<<< HEAD
        return naturalnessCategoryRepository.findByName(naturalnessName)
=======
        return naturalnessCategoryRepository.findNaturalnessCategoryByTitle(naturalnessName)
<<<<<<< HEAD
>>>>>>> d3cc878 (Мигрировал на mongodb)
                .orElseGet(() -> new NaturalnessCategory(naturalnessName));
=======
                .defaultIfEmpty(new NaturalnessCategory(naturalnessName))
                .block();
>>>>>>> d735fd6 (Реализовал реактивный поиск компонента с асинхронным добавлением компонента в базу)
    }
}
