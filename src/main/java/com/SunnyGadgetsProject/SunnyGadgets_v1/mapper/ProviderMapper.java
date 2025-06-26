package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProviderCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProviderResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ProviderMapper {
    // CreateDTO → Entity
    Provider toEntity(ProviderCreateDTO dto);

    // Entity → ResponseDTO
    ProviderResponseDTO toDto(Provider provider);
}
