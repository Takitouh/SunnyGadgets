package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Permission;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Role;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryPermission;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryRole;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ServiceRole implements IServiceRole{

    private final IRepositoryRole roleRepository;
    private final IRepositoryPermission permissionRepository;

    public ServiceRole(IRepositoryPermission permissionRepository, IRepositoryRole roleRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role createRole(Role role) {
        Set<Permission> managedPermissions = new HashSet<>();

        for (Permission permission : role.getPermissions()) {
            Permission managedPermission = permissionRepository.findById(permission.getId())
                    .orElseThrow(() -> new RuntimeException("Permiso con ID " + permission.getId() + " no encontrado"));
            managedPermissions.add(managedPermission);
        }
        System.out.println(managedPermissions);
        role.setPermissions(managedPermissions); // Usas el Set limpio
        return roleRepository.save(role);
    }

    @Override
    public List<Role> createRole(Set<Role> roles) {
        return roleRepository.saveAll(roles);
    }


    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
