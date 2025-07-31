package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record PermissionCreateDTO(
        @Schema(description = "Name of the permission", example = "READ")
        @NotBlank(message = "Permission can't be blank or null")
        String permissionName
) {
}
