package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.PermissionCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.PermissionResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface PermissionMapper {
    // CreateDTO → Entity
    Permission toEntity(PermissionCreateDTO dto);

    // Entity → ResponseDTO
    PermissionResponseDTO toDto(Permission permission);
}
