package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

public record ProductResponseDTO(String name, String description, long price, int stock,
                                 CategoryResponseDTO categoryResponseDTO) {
}
