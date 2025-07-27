package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

public record UserSecCreateDTO(
    @NotBlank(message = "Username can't be blank or null")
    String username,

    @NotBlank(message = "Password can't be blank or null")
    String password,

    @NotNull
    Boolean enabled,

    @NotNull
    Boolean accountNotExpired,

    @NotNull
    Boolean credentialsNotExpired,

    @NotNull
    Boolean accountNotLocked,

    Set<@NotNull(message = "The user must have at least one role, please provide one valid ID") Long> existingRolesIds
) {

}
