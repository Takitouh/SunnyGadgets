package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import java.util.Set;

public record RolePatchDTO(
        String role,

        Set<Long> permissionsIds
) {

}