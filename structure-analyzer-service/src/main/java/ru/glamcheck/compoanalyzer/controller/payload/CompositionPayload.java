package ru.glamcheck.compoanalyzer.controller.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Состав косметики")
public class CompositionPayload {

    @Schema(description = "Перечисленные компоненты", example = "Chamomilla Recutita Flower Extract, aqua, COCAMIDOPROPYL BETAINE, COCO-GLUCOSIDE")
    private String composition;

}
