package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;


public record DetailSaleResponseDTO(int quantity, long subtotal, long unitPrice
        , ProductResponseDTO product) {
}
