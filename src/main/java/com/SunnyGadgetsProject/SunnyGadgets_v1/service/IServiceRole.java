package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IServiceRole {
    List<Role> allRoles();
    Optional<Role> getRoleById(Long id);
    Role createRole(Role role);
    List<Role> createRole(Set<Role> role);
    void deleteRole(Long id);
    Role updateRole(Role role);
}
