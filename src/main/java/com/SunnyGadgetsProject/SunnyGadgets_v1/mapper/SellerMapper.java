package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SellerCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SellerResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositorySale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {SaleMapper.class})

public abstract class SellerMapper {

    @Autowired
    protected IRepositorySale repositorySale;
    // CreateDTO → Entity
    @Mapping(target = "idEmployee", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "sales", ignore = true)
    Seller toEntity(SellerCreateDTO dto);

    // Entity → ResponseDTO
    @Mapping(target = "idSeller", source = "idEmployee")
    @Mapping(target = "createdAt", source = "createdAt")

    public abstract SellerResponseDTO toDto(Seller seller);
    @SuppressWarnings("unused")
    public Sale saleDTOtoSale(Long id){
        return repositorySale.findById(id).orElse(new Sale());
    }
}
