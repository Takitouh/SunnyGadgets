package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProductCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProductResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryCategory;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProvider;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    protected IRepositoryCategory repositoryCategory;

    @Autowired
    protected IRepositoryProvider repositoryProvider;

    // CreateDTO → Entity
    @Mapping(target = "category", source = "idCategory")
    @Mapping(target = "idProduct", ignore = true)
    @Mapping(target = "setProviders", source = "existingProvidersIds")
    public abstract Product toEntity(ProductCreateDTO dto);

    // Entity → ResponseDTO
    public abstract ProductResponseDTO toDto(Product product);
    @SuppressWarnings("unused")
    protected Category mapCategory(Long id) {
        if (id == null) {
            throw new NullPointerException("Category is null");
        }

        return repositoryCategory.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @SuppressWarnings("unused")
    protected String mapCategoryName(Category category) {
        if (category == null) {
            throw new NullPointerException("Category is null");
        }
        return category.getName();
    }
    @SuppressWarnings("unused")
    protected Set<Provider> mapProviders(Set<Long> ids) {
        if (ids == null) {
            throw new NullPointerException("ids is null");
        }
        return ids.stream()
                .map(id -> repositoryProvider.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Permission with id " + id + " doesn't exist")))
                .collect(Collectors.toSet());
    }
}
