package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Role;

import java.util.Set;

public record UserSecResponseDTO(Long idUser, String username, String password, Set<Role> roles, boolean accountNotExpired, boolean accountNotLocked,
                                 boolean credentialsNotExpired, boolean enabled) {
}
