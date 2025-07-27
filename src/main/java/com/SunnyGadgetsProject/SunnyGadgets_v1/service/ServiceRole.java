package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RoleCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RolePatchDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RolePutDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RoleResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Permission;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Role;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.RoleMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryPermission;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryRole;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceRole implements IServiceRole{
    private final Logger logger = LoggerFactory.getLogger(ServiceRole.class);
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
        logger.info("Get all roles from repository");
        return roles;
    }

    @Override
    public RoleResponseDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        logger.info("Get role from repository");
        return roleMapper.toDto(role);
    }

    @Override
    public RoleResponseDTO createRole(RoleCreateDTO role) {
        RoleResponseDTO roleResponseDTO = proccessRole(role);
        logger.info("Created role");
        return roleResponseDTO;
    }

    @Override
    public List<RoleResponseDTO> createRole(Set<RoleCreateDTO> roles) {
        List<RoleResponseDTO> roleResponseDTOs = new ArrayList<>();
        for (RoleCreateDTO role : roles) {
            roleResponseDTOs.add(proccessRole(role));
        }
        logger.info("Created list of role");
        return roleResponseDTOs;
    }


    @Override
    public RoleResponseDTO updateRole(RolePutDTO rolePutDTO, Long id) {
        Role role = roleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Set<Permission> rolePermissions = rolePutDTO.permissionsIds().stream().map(permissionId -> permissionRepository.findById(permissionId).orElseThrow(() -> new EntityNotFoundException("Permission with id: " + permissionId + " not found"))).collect(Collectors.toSet());
        role.setPermissions(rolePermissions);
        role.setRole(rolePutDTO.role());
        //Persist
        roleRepository.save(role);
        //Map the entity to DTO for response
        logger.info("Role updated with PUT: {}", role);
        return roleMapper.toDto(role);
    }

    @Override
    public RoleResponseDTO updateRole(RolePatchDTO rolePatchDTO, Long id) {
        Role role = roleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Set<Long> permissionIds = rolePatchDTO.permissionsIds() == null? new HashSet<>() : rolePatchDTO.permissionsIds();
        Set<Permission> rolePermissions = permissionIds.isEmpty()? role.getPermissions() : rolePatchDTO.permissionsIds().stream().map(permissionId -> permissionRepository.findById(permissionId).orElseThrow(() -> new EntityNotFoundException("Permission with id: " + permissionId + " not found"))).collect(Collectors.toSet());

        role.setPermissions(rolePermissions);
        role.setRole(rolePatchDTO.role() == null || rolePatchDTO.role().isEmpty()? role.getRole() : rolePatchDTO.role());
        //Persist
        roleRepository.save(role);
        //Map the entity to DTO for response
        logger.info("Role updated with PATCH: {}", role);
        return roleMapper.toDto(role);    }

    @Override
    public void deleteRole(Long id) {
        logger.info("Delete role from repository");
        roleRepository.deleteById(id);
    }

    private RoleResponseDTO proccessRole(RoleCreateDTO role) {
        Role roleEntity;
        RoleResponseDTO roleResponseDTO;
        Set<Permission> rolePermissions = new HashSet<>();
        if (role.permissionsIds().isEmpty()) {
            throw new EntityNotFoundException("The role must have at least one permission");
        }
        for (Long permissionId : role.permissionsIds()) {
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
        roleResponseDTO = roleMapper.toDto(roleEntity);
        return roleResponseDTO;
    }

}
