package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SaleCreateDTO(

    @NotNull(message = "ID of customer can't be null")
    Long idCustomer,

    @NotNull(message = "ID of seller can't be null")
    Long idSeller,


    @NotEmpty(message = "The sale must have at least one detail sale") @Valid
    List<@NotNull(message = "Details of the sale can't be null")DetailSaleCreateDTO> listdetailSale
) {}
