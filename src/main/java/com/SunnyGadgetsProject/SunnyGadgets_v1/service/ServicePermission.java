package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Permission;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryPermission;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicePermission  implements  IServicePermission{

    private final IRepositoryPermission permissionRepository;

    public ServicePermission(IRepositoryPermission permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<Permission> allPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional<Permission> getPermissionById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> createPermission(Set<Permission> permissions) {
        return permissionRepository.saveAll(permissions);
    }


    @Override
    public Permission updatePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}

