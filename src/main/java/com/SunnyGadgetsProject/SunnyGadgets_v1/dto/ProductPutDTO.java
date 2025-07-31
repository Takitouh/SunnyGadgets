package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.Set;

public record ProductPutDTO(
        @Schema(description = "Name of the product", example = "Lenovo gaming laptop LEGION")
        @NotNull(message = "The name of the product can't be null") @NotBlank(message = "The name of the product can't be blank")
        String name,

        @Schema(description = "Description of the product", example = "gaming laptop with graphic card Nvidia and CPU Intel")
        String description,

        @Schema(description = "Price of the product", example = "500")
        @NotNull(message = "The price can't be null") @Positive(message = "The price can't be negative or zero")
        Long price,

        @Schema(description = "Stock of the product", example = "15")
        @NotNull(message = "The stock can't be null") @Positive(message = "The stock can't be negative or zero")
        Integer stock,

        @Schema(description = "ID of the category whom belong the product", example = "1")
        @NotNull(message = "The product must have at least one category")
        Long idCategory,

        @Schema(description = "ID's of providers who provide the product", example = "2")
        Set<@NotNull(message = "The product must have at least one provider") Long> existingProvidersIds) {

}
