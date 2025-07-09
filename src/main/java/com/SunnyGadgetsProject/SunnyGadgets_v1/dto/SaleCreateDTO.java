package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SaleCreateDTO {

    @Positive(message = "The total can't be negative or zero")
    private long total;

    @NotNull(message = "ID of customer can't be null")
    private Long idCustomer;

    @NotNull(message = "ID of seller can't be null")
    private Long idSeller;
    @NotNull(message = "Details of the sale can't be null")
    @NotEmpty(message = "The sale must have at least one detail sale") @Valid
    private List<DetailSaleCreateDTO> listdetailSale;
}
