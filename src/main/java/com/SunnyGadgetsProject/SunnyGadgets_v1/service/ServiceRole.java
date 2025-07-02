package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RoleCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RoleResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Permission;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Role;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.RoleMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryPermission;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryRole;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceRole implements IServiceRole{

    private final IRepositoryRole roleRepository;
    private final IRepositoryPermission permissionRepository;
    private final RoleMapper roleMapper;

    public ServiceRole(IRepositoryRole roleRepository, IRepositoryPermission permissionRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleResponseDTO> allRoles() {
        List<RoleResponseDTO> roles = new ArrayList<>();
        for (Role r : roleRepository.findAll()) {
            roles.add(roleMapper.toDto(r));
        }
        if (roles.isEmpty()) {
            throw new EntityNotFoundException("No roles found"); //Excepcion Not Found
        }
        return roles;
    }

    @Override
    public RoleResponseDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return roleMapper.toDto(role);
    }

    @Override
    public RoleResponseDTO createRole(RoleCreateDTO role) {
       // Set<Permission> managedPermissions = new HashSet<>();
        Role roleEntity;
        RoleResponseDTO roleResponseDTO;
        Set<Permission> rolePermissions = new HashSet<>();
        if (role.getPermissionsIds().isEmpty()) {
            throw new EntityNotFoundException("The role must have at least one permission");
        }
        for (Long permissionId : role.getPermissionsIds()) {
            //We use the set of ID'S for search and store each one of the permissions
            if (permissionRepository.findById(permissionId).isEmpty()) {
                throw new EntityNotFoundException("Permission with id " + permissionId + " not found");
            }
//            Permission managedPermission = permissionRepository.findById(permissionId)
//                    .orElseThrow(() -> new RuntimeException("Permission with ID " + permissionId + " not found"));
//            managedPermissions.add(managedPermission);
            //And add to the set of the permissions of the role
            rolePermissions.add(permissionRepository.findById(permissionId).get());
        }
        //Set the values to the entity role
        roleEntity = roleMapper.toEntity(role);
        roleEntity.setPermissions(rolePermissions);
        //Persist
        roleRepository.save(roleEntity);
        //Map the entity to DTO for response
        roleResponseDTO = roleMapper.toDto(roleEntity);

        return roleResponseDTO;
    }

    @Override
    public List<RoleResponseDTO> createRole(Set<RoleCreateDTO> roles) {
        return null;//roleRepository.saveAll(roles);
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
