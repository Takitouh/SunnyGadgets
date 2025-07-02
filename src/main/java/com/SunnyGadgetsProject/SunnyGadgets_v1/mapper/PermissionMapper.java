package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.PermissionCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.PermissionResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface PermissionMapper {
    // CreateDTO → Entity
    @Mapping(target = "idPermission", ignore = true)
    Permission toEntity(PermissionCreateDTO dto);

    // Entity → ResponseDTO
    @Mapping(target = "idPermission", source = "idPermission")
    PermissionResponseDTO toDto(Permission permission);
}
