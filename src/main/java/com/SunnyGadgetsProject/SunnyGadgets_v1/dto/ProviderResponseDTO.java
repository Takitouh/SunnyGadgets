package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import java.sql.Timestamp;
import java.util.Set;

public record ProviderResponseDTO(Long idProvider, String name, String phoneNumber, String email, long salary,
                                  Timestamp createdAt, Timestamp updatedAt, Set<ProductResponseDTO> productSet,
                                  String company) {
}
