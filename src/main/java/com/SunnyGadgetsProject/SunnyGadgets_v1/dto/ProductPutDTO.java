package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.*;

import java.util.Set;

public record ProductPutDTO(
        @NotNull(message = "The name of the product can't be null") @NotBlank(message = "The name of the product can't be blank")
        String name,
        String description,
        @NotNull(message = "The price can't be null") @Positive(message = "The price can't be negative or zero")
        Long price,
        @NotNull(message = "The stock can't be null") @Positive(message = "The stock can't be negative or zero")
        Integer stock,
        @NotNull(message = "The product must have at least one category")
        Long idCategory,
        Set<@NotNull(message = "The product must have at least one provider") Long> existingProvidersIds) {

}