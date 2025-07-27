package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DetailSalePutDTO(@Positive(message = "You must order at least one product")
                               int quantity,
                               @NotNull(message = "You must order some product, please provide one ID from a product valid")
                               Long product) {
}
