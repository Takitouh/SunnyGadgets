package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import java.util.Date;
import java.util.Set;

public record SellerResponseDTO(String name, String phoneNumber, long salary,
                                String createdAt, String updatedAt, long comission,
                                Date hireDate, Set<SaleResponseDTO> sales) {
}
