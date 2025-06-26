package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;


import java.util.List;

public record SaleResponseDTO(String createdAt, long total, CustomerResponseDTO customerResponseDTO,
                              SellerResponseDTO sellerResponseDTO, List<DetailSaleResponseDTO> detailSaleResponseDTO) {
}
