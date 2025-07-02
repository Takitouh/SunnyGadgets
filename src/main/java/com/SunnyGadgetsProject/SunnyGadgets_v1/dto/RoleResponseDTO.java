package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import java.util.Set;

public record RoleResponseDTO(Long idRole, String role, Set<PermissionResponseDTO> permissions) {
}
