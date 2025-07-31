package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record RoleCreateDTO(
        @Schema(description = "Name of the role", example = "ADMIN")
        @NotBlank(message = "The name of the role can't be blank or null")
        String role,
        @Schema(description = "ID's of permissions", example = "1, 2, 3")
        @NotEmpty(message = "The role must have at least one permission")
        Set<@NotNull(message = "The role must have at least one permission") Long> permissionsIds
) {
}
