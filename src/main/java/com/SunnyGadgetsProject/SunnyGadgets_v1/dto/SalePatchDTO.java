package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public record SalePatchDTO(
        Optional<Long> idCustomer,

        Optional<Long> idSeller,

        @Valid
        List<DetailSalePatchDTO> listdetailSale
) {}