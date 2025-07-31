package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CategoryCreateDTO(
        @Schema(name = "name", description = "Name of the category", example = "Gaming PC", type = "String")
        @NotBlank(message = "Name can't be blank or null")
        String name,
        @Schema(name = "description", description = "Description of the category", example = "Contains a variety of gaming pc's of each" +
                "gamma", type = "String")
        String description) {

}
