package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.PermissionCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.PermissionResponseDTO;

import java.util.List;
import java.util.Set;

public interface IServicePermission {
    List<PermissionResponseDTO> allPermissions();
    PermissionResponseDTO getPermissionById(Long id);
    PermissionResponseDTO createPermission(PermissionCreateDTO permission);
    List<PermissionResponseDTO> createPermission(Set<PermissionCreateDTO> permission);
    PermissionResponseDTO updatePermission(PermissionCreateDTO permission, Long id);
    void deletePermission(Long id);

}
