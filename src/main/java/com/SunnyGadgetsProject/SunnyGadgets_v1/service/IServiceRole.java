package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RoleCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RoleResponseDTO;

import java.util.List;
import java.util.Set;

public interface IServiceRole {
    List<RoleResponseDTO> allRoles();
    RoleResponseDTO getRoleById(Long id);
    RoleResponseDTO createRole(RoleCreateDTO role);
    List<RoleResponseDTO> createRole(Set<RoleCreateDTO> role);
    void deleteRole(Long id);
    RoleResponseDTO updateRole(RoleCreateDTO role, Long id);
}
