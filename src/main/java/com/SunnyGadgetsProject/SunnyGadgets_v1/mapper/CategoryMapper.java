package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    // CreateDTO → Entity
    Category toEntity(CategoryCreateDTO dto);

    // Entity → ResponseDTO
    CategoryResponseDTO toDto(Category category);
}
