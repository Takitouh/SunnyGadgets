package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Role;

import java.util.List;
import java.util.Optional;

public interface IServiceRole {
    List<Role> allRoles();
    Optional<Role> getRoleById(Long id);
    Role createRole(Role role);
    void deleteRole(Long id);
    Role updateRole(Role role);
}
