package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SalePutDTO(
        @NotNull(message = "The customer can't be null, please provide one ID valid")
        Long idCustomer,

        @NotNull(message = "The seller can't be null, please provide one ID valid")
        Long idSeller,

        @Valid
        List<@NotNull(message = "Details of the sale can't be null") DetailSalePutDTO> listdetailSale
) {
}