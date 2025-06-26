package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;


public record DetailSaleResponseDTO(int quantity, long total, long unitPrice, SaleResponseDTO sales
                                    ,ProductResponseDTO products) {
}
