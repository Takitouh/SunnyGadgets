package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import java.util.HashSet;
import java.util.Set;

public record UserSecPatchDTO(
    String username,

    String password,


    Boolean enabled,


    Boolean accountNotExpired,


    Boolean credentialsNotExpired,


    Boolean accountNotLocked,

    Set<Long> existingRolesIds
) {
    public UserSecPatchDTO {
        if (existingRolesIds == null) {
            existingRolesIds = new HashSet<>();
        }
    }
}