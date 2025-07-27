package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.NotBlank;

public record PermissionCreateDTO(
    @NotBlank(message = "Permission can't be blank or null")
    String permissionName
) {}
