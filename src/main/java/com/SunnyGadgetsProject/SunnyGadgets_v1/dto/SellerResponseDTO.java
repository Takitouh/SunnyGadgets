package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import java.sql.Timestamp;
import java.util.Set;

public record SellerResponseDTO(Long idSeller, String name, String phoneNumber, long salary,
                                Timestamp createdAt, Timestamp updatedAt, long commission) {
}
