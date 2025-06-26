package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SaleCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SaleResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface SaleMapper {
    // CreateDTO → Entity
    Sale toEntity(SaleCreateDTO dto);

    // Entity → ResponseDTO
    SaleResponseDTO toDto(Sale sale);
}
