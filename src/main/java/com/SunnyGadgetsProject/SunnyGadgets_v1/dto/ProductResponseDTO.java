package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import java.util.Set;

public record ProductResponseDTO(Long idProduct, String name, String description, long price, int stock,
                                 String category) {
}
