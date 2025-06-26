package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import java.util.Set;

public record ProviderResponseDTO(String name, String phoneNumber, String email, long salary,
                                  String createdAt, String updatedAt, Set<ProductResponseDTO> products,
                                  String company) {
}
