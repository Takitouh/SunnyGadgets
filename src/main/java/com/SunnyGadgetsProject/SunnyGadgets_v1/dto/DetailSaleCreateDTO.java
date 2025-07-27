package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DetailSaleCreateDTO(
    @Positive(message = "You must order at least one product")
    int quantity,


    @NotNull(message = "The ID of product can't be null")
    Long product
) {}
