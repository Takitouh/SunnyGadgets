package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SaleCreateDTO(
        @Schema(description = "ID of the customer who is going to purchase", example = "1")
        @NotNull(message = "ID of customer can't be null")
        Long idCustomer,
        @Schema(description = "ID of the seller who is going to sell", example = "2")
        @NotNull(message = "ID of seller can't be null")
        Long idSeller,
        @Schema(description = "List of detail sale that contain quantity, product, subtotal, unit price", example = "detailsale : [" +
                "quantity : 1," +
                "product : 1," +
                "]")
        @NotEmpty(message = "The sale must have at least one detail sale") @Valid
        List<@NotNull(message = "Details of the sale can't be null") DetailSaleCreateDTO> listdetailSale
) {
}
