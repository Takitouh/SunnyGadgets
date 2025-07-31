package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public abstract class CategoryMapper {

    @Autowired
    protected IRepositoryProduct repositoryProduct;

    // CreateDTO → Entity
    @Mapping(target = "idCategory", ignore = true)
    @Mapping(target = "productSet", ignore = true)
    public abstract Category toEntity(CategoryCreateDTO dto);
    // Entity → ResponseDTO
    public abstract CategoryResponseDTO toDto(Category category);

    @SuppressWarnings("unused")
    protected Set<Product> mapProducts(Set<Long> ids) {
        if (ids == null) {
            return new HashSet<>();
        }
        return ids.stream()
                .map(id -> repositoryProduct.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Product with id " + id + " doesn't exist")))
                .collect(Collectors.toSet());
    }
}
