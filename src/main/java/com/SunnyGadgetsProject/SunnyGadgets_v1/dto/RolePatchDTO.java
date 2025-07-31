package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;

public record RolePatchDTO(
        @Schema(description = "Name of the role", example = "ADMIN")
        String role,

        @Schema(description = "ID's of permissions", example = "1, 2, 3")
        Set<Long> permissionsIds
) {

}
