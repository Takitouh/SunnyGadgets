package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PermissionPatchDTO(
        @Schema(description = "Name of the permission", example = "READ")
        String permissionName
) {}
