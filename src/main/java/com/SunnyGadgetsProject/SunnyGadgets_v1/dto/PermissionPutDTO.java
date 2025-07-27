package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PermissionPutDTO(
    @NotBlank(message = "Permission name can't be blank or null")
    String permissionName
) {}