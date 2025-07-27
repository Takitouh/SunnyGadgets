package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.DetailSaleCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.DetailSalePatchDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.DetailSalePutDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.DetailSaleResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.DetailSale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public abstract class DetailSaleMapper {
    @Autowired
    protected IRepositoryProduct repositoryProduct;
    // PutDTO -> Entity
    @Mapping(target = "idDetailSale", ignore = true)
    @Mapping(target = "sale", ignore = true)
    @Mapping(target = "unitPrice", ignore = true)
    @Mapping(target = "subtotal", ignore = true)
    @SuppressWarnings("unused")
    public abstract DetailSale toPatchDTO(DetailSalePutDTO detailSalePutDTO);
    // PatchDTO -> Entity
    @Mapping(target = "idDetailSale", ignore = true)
    @Mapping(target = "sale", ignore = true)
    @Mapping(target = "unitPrice", ignore = true)
    @Mapping(target = "subtotal", ignore = true)
    @SuppressWarnings("unused")
    public abstract DetailSale toPatchDTO(DetailSalePatchDTO detailSalePatchDTO);
    // CreateDTO -> Entity
    @Mapping(target = "idDetailSale", ignore = true)
    @Mapping(target = "sale", ignore = true)
    @Mapping(target = "unitPrice", ignore = true)
    @Mapping(target = "subtotal", ignore = true)
    public abstract DetailSale toEntity(DetailSaleCreateDTO detailSaleCreateDTO);

    // Entity → ResponseDTO
    public abstract DetailSaleResponseDTO toDto(DetailSale detailSale);
    @SuppressWarnings("unused")
    public Product toFindProductById(Long id){
        return repositoryProduct.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
    }
    @SuppressWarnings("unused")
    public Product toFindOptionalProductById(Optional<Long> id){

        return id.map(idP -> repositoryProduct.findById(idP).orElseThrow(()->new RuntimeException("Product not found"))).orElse(null);
    }

}
