package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import java.util.Set;

public record CustomerResponseDTO(String name, String address, int age, String email, String phoneNumber, String
                                  createdAt, String updatedAt, Set<SaleResponseDTO> sales) {
}
