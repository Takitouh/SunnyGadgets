package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Permission;

import java.util.List;
import java.util.Optional;

public interface IServicePermission {
    List<Permission> allPermissions();
    Optional<Permission> getPermissionById(Long id);
    Permission createPermission(Permission permission);
    List<Permission> createPermission(Set<Permission> permission);
    Permission updatePermission(Permission permission);
    void deletePermission(Long id);

}
