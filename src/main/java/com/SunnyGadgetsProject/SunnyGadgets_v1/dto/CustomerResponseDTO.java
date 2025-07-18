package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import java.sql.Timestamp;
import java.util.Set;

public record CustomerResponseDTO(Long idCustomer, String name, String address, int age, String email, String phoneNumber, Timestamp
                                  createdAt, Timestamp updatedAt) {
}
