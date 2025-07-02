package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RoleCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RoleResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Permission;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Role;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryPermission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")

public abstract class RoleMapper {

    @Autowired
    protected  IRepositoryPermission repositoryPermission;

    @SuppressWarnings("unused")
    protected Set<Permission> mapPermissions(Set<Long> ids) {
        if (ids == null) {
            throw new NullPointerException("ids is null");
        }
        return ids.stream()
                .map(id -> repositoryPermission.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Permission with id " + id + " doesn't exist")))
                .collect(Collectors.toSet());
    }
    // CreateDTO → Entity
    @Mapping(target = "permissions", source = "permissionsIds")
    @Mapping(target = "idRole", ignore = true)
    public abstract Role toEntity(RoleCreateDTO dto);

    // Entity → ResponseDTO
    //@Mapping(target = "idRole", source = "idRole")
    public abstract RoleResponseDTO toDto(Role role);
}
