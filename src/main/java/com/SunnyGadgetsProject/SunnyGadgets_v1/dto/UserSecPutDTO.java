package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UserSecPutDTO(
    @Schema(description = "name of the user", example = "Manager")
    @NotBlank(message = "Username can't be blank or null")
    String username,

    @Schema(description = "Password", example = "12345")
    @NotBlank(message = "Password can't be blank or null")
    String password,

    @Schema(description = "Is the user enabled?", example = "TRUE")
    @NotNull
    Boolean enabled,

    @Schema(description = "Is the account of the user not expired?", example = "TRUE")
    @NotNull
    Boolean accountNotExpired,

    @Schema(description = "Is the credentials of the user not expired?", example = "TRUE")
    @NotNull
    Boolean credentialsNotExpired,

    @Schema(description = "Is the account of the user not locked?", example = "TRUE")
    @NotNull
    Boolean accountNotLocked,

    @Schema(description = "ID's of the Role that the user has", example = "1, 5")
    Set<@NotNull(message = "The user must have at least one role, please provide one valid ID") Long> existingRolesIds
) {
}
