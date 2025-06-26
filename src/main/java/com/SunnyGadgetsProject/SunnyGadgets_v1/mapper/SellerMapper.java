package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SellerCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SellerResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface SellerMapper {
    // CreateDTO → Entity
    Seller toEntity(SellerCreateDTO dto);

    // Entity → ResponseDTO
    SellerResponseDTO toDto(Seller seller);
}
