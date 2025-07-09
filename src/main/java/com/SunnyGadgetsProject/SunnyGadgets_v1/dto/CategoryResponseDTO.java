package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import java.util.Set;

public record CategoryResponseDTO(Long idCategory, String name, String description, Set<ProductResponseDTO> productSet) {}
