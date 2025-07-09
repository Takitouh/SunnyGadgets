package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;


import java.sql.Timestamp;
import java.util.List;

public record SaleResponseDTO(Long idSale, Timestamp salecreatedAt,  long total, String customerName,
                              String sellerName, List<DetailSaleResponseDTO> listdetailSale) {
}
