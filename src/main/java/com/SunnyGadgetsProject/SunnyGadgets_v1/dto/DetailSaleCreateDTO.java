package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DetailSaleCreateDTO(
        @Schema(description = "Quantity of product to sell", example = "1")
        @Positive(message = "You must order at least one product")
        int quantity,

        @Schema(description = "ID of product to sell", example = "2")
        @NotNull(message = "The ID of product can't be null")
        Long product
) {
}
