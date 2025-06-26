package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProductCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProductResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ProductMapper {
    // CreateDTO → Entity
    Product toEntity(ProductCreateDTO dto);

    // Entity → ResponseDTO
    ProductResponseDTO toDto(Product product);
}
