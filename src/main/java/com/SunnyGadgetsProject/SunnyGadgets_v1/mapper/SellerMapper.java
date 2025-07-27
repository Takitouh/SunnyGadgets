package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SellerCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SellerPatchDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SellerPutDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SellerResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SellerMapper {
    // PatchDTO -> Entity
    @Mapping(target = "idEmployee", ignore = true)
    @Mapping(target = "sales", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Seller toEntity(SellerPatchDTO dto);
    // PutDTO -> Entity
    @Mapping(target = "idEmployee", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "sales", ignore = true)
    Seller toEntity(SellerPutDTO dto);
    // CreateDTO → Entity
    @Mapping(target = "idEmployee", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "sales", ignore = true)
    Seller toEntity(SellerCreateDTO dto);

    // Entity → ResponseDTO
    @Mapping(target = "idSeller", source = "idEmployee")
    SellerResponseDTO toDto(Seller seller);

}
