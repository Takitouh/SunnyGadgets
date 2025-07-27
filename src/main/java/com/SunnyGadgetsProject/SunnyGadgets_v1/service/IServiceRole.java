package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RoleCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RolePatchDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RolePutDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RoleResponseDTO;

import java.util.List;
import java.util.Set;

public interface IServiceRole {
    List<RoleResponseDTO> allRoles();
    RoleResponseDTO getRoleById(Long id);
    RoleResponseDTO createRole(RoleCreateDTO role);
    List<RoleResponseDTO> createRole(Set<RoleCreateDTO> role);
    RoleResponseDTO updateRole(RolePutDTO role, Long id);
    RoleResponseDTO updateRole(RolePatchDTO role, Long id);
    void deleteRole(Long id);
}
