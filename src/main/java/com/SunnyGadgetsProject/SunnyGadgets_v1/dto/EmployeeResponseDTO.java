package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

public record EmployeeResponseDTO(String name, String phoneNumber, String email, long salary,
                                  String createdAt, String updatedAt) {
}
