package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProviderCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProviderResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public abstract class  ProviderMapper {

    @Autowired
    protected IRepositoryProduct repositoryProduct;


    // CreateDTO → Entity
    @Mapping(target = "productSet", source = "existentProductsIds")
    @Mapping(target = "idEmployee", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract Provider toEntity(ProviderCreateDTO dto);

    // Entity → ResponseDTO
    @Mapping(target = "idProvider", source = "idEmployee")
    public abstract ProviderResponseDTO toDto(Provider provider);

    @SuppressWarnings("unused")
    protected Set<Product> mapProduct(Set<Long> ids) {
        if (ids == null) {
            throw new NullPointerException("Provider must have at least one product so provide at least one valid id");
        }
        return ids.stream()
                .map(id -> repositoryProduct.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Product with id " + id + " doesn't exist")))
                .collect(Collectors.toSet());
    }

}
