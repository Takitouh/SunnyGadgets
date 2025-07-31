package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record PermissionPutDTO(
    @Schema(description = "Name of the permission", example = "READ")
    @NotBlank(message = "Permission name can't be blank or null")
    String permissionName
) {}
