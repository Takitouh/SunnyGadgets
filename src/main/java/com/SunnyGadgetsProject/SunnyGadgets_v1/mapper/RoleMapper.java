package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RoleCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RoleResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface RoleMapper {
    // CreateDTO → Entity
    Role toEntity(RoleCreateDTO dto);

    // Entity → ResponseDTO
    RoleResponseDTO toDto(Role role);
}
