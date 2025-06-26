package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.UserSec;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserMapper {
    // CreateDTO → Entity
    UserSec toEntity(UserSecCreateDTO dto);

    // Entity → ResponseDTO
    UserSecResponseDTO toDto(UserSec userSec);
}
