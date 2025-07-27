package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record RolePutDTO(
    @NotBlank(message = "The name of the role can't be blank or null")
    String role,

    Set<@NotNull(message = "The role must have at least one permission") Long> permissionsIds
) {
}