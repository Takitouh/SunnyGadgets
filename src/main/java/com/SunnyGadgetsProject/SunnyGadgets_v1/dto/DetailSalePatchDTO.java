package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.Positive;

import java.util.Optional;

public record DetailSalePatchDTO(@Positive(message = "You must order at least one product")
                                 int quantity,

                                 Optional<Long> product) {
}
