package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecPatchDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecPutDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Role;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.UserSec;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected IRepositoryRole repositoryRole;
    // PatchDTO -> Entity
    @Mapping(target = "roles", source = "existingRolesIds")
    @Mapping(target = "idUser", ignore = true)
    public abstract UserSec toEntity(UserSecPatchDTO dto);
    // PutDTO -> Entity
    @Mapping(target = "roles", source = "existingRolesIds")
    @Mapping(target = "idUser", ignore = true)
    public abstract UserSec toEntity(UserSecPutDTO dto);
    // CreateDTO → Entity
    @Mapping(target = "roles", source = "existingRolesIds")
    @Mapping(target = "idUser", ignore = true)
    public abstract UserSec toEntity(UserSecCreateDTO dto);

    // Entity → ResponseDTO
    public abstract UserSecResponseDTO toDto(UserSec userSec);
    @SuppressWarnings("unused")
    protected Set<Role> mapRoles(Set<Long> ids) {
        if (ids == null) {
            throw new NullPointerException("ids is null");
        }
        return ids.stream()
                .map(id -> repositoryRole.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Role with id " + id + " doesn't exist")))
                .collect(Collectors.toSet());
    }

}
