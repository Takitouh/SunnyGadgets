package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.*;

import java.util.Set;

public record ProductPatchDTO(
        String name,
        String description,
        @Positive(message = "The price can't be negative or zero")
        Long price,
        @Positive(message = "The stock can't be negative or zero")
        Integer stock,
        Long idCategory,
        Set<Long> existingProvidersIds) {

}