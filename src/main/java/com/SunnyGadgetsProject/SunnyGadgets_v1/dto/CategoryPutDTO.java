package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CategoryPutDTO(
        @Schema(description = "Name of the category", example = "Gaming PC")
        @NotBlank(message = "Name can't be blank or null")
        String name,
        @Schema(description = "Description of the category", example = "Contains a variety of gaming pc's of each" +
                "gamma")
        String description) {
}