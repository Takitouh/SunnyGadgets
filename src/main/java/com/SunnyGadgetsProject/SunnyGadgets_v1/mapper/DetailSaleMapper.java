package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.DetailSaleCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.DetailSaleResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.DetailSale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProduct;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public abstract class DetailSaleMapper {
    @Autowired
    protected IRepositoryProduct repositoryProduct;
    // CreateDTO -> Entity
    public abstract DetailSale toEntity(DetailSaleCreateDTO detailSaleCreateDTO);

    // Entity → ResponseDTO
    public abstract DetailSaleResponseDTO toDto(DetailSale detailSale);
    @SuppressWarnings("unused")
    public Product toFindProductById(Long id){
        return repositoryProduct.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
    }

}
