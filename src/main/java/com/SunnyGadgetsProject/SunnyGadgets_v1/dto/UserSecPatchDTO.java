package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashSet;
import java.util.Set;

public record UserSecPatchDTO(
    @Schema(description = "name of the user", example = "Manager")
    String username,

    @Schema(description = "Password", example = "12345")
    String password,

    @Schema(description = "Is the user enabled?", example = "TRUE")
    Boolean enabled,

    @Schema(description = "Is the account of the user not expired?", example = "TRUE")
    Boolean accountNotExpired,

    @Schema(description = "Is the credentials of the user not expired?", example = "TRUE")
    Boolean credentialsNotExpired,

    @Schema(description = "Is the account of the user not locked?", example = "TRUE")
    Boolean accountNotLocked,

    @Schema(description = "ID's of the Role that the user has", example = "1, 5")
    Set<Long> existingRolesIds
) {
    public UserSecPatchDTO {
        if (existingRolesIds == null) {
            existingRolesIds = new HashSet<>();
        }
    }
}
