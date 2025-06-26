package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DetailSaleCreateDTO {
    @Positive(message = "You must order at least one product")
    private int quantity;

    private long unitPrice;

    private long subtotal;

    @Valid @NotNull
    private SaleCreateDTO sale;

    @Valid @NotNull
    private ProductCreateDTO product;
}
