package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.Set;

public record ProductPatchDTO(
        @Schema(description = "Name of the product", example = "Lenovo gaming laptop LEGION")
        String name,

        @Schema(description = "Description of the product", example = "gaming laptop with graphic card Nvidia and CPU Intel")
        String description,

        @Schema(description = "Price of the product", example = "500")
        @Positive(message = "The price can't be negative or zero")
        Long price,

        @Schema(description = "Stock of the product", example = "15")
        @Positive(message = "The stock can't be negative or zero")
        Integer stock,

        @Schema(description = "ID of the category whom belong the product", example = "1")
        Long idCategory,

        @Schema(description = "ID's of providers who provide the product", example = "2")
        Set<Long> existingProvidersIds) {

}
