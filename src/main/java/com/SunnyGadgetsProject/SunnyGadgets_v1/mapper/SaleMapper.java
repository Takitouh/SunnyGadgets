package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.DetailSale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryCustomer;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProduct;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositorySeller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", uses = {DetailSaleMapper.class})

public abstract class SaleMapper {
    @Autowired
    protected IRepositoryCustomer repositoryCustomer;

    @Autowired
    protected IRepositorySeller repositorySeller;

    @Autowired
    protected IRepositoryProduct repositoryProduct;
    //PatchDTO -> Entity
    @Mapping(target = "idSale", ignore = true)
    @Mapping(target = "salecreatedAt", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "customer", source = "idCustomer")
    @Mapping(target = "seller", source = "idSeller")

    public abstract Sale toEntity(SalePatchDTO dto);
    // PutDTO -> Entity
    @Mapping(target = "idSale", ignore = true)
    @Mapping(target = "salecreatedAt", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "customer", source = "idCustomer")
    @Mapping(target = "seller", source = "idSeller")
    public abstract Sale toEntity(SalePutDTO dto);
    // CreateDTO → Entity
    @Mapping(target = "idSale", ignore = true)
    @Mapping(target = "salecreatedAt", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "customer", source = "idCustomer")
    @Mapping(target = "seller", source = "idSeller")
    public abstract Sale toEntity(SaleCreateDTO dto);

    // Entity → ResponseDTO
    @Mapping(target = "customerName", source = "customer")
    @Mapping(target = "sellerName", source = "seller")
    public abstract SaleResponseDTO toDto(Sale sale);

    //method to find the customer by id
    @SuppressWarnings("unused")
    public  Customer toFindCustomerById(Long id){

        return repositoryCustomer.findById(id).orElseThrow(()->new RuntimeException("Customer not found"));
    }

    //method to find the seller by id
    @SuppressWarnings("unused")
    public Seller toFindOptionalSellerById(Optional<Long> id){
        return id.map(aLong -> repositorySeller.findById(aLong).orElseThrow(() -> new RuntimeException("Seller not found"))).orElse(null);
    }
    //method to find the customer by id
    @SuppressWarnings("unused")
    public  Customer toFindOptionalCustomerById(Optional<Long> id){
        return id.map(aLong -> repositoryCustomer.findById(aLong).orElseThrow(() -> new RuntimeException("Customer not found"))).orElse(null);
    }

    //method to find the seller by id
    @SuppressWarnings("unused")
    public Seller toFindSellerById(Long id){
        return repositorySeller.findById(id).orElseThrow(()->new RuntimeException("Seller not found"));
    }
    @SuppressWarnings("unused")
    public String getNameSeller(Seller seller){
        if(seller == null){
            throw new RuntimeException("Name of seller not found");
        }
        return seller.getName();
    }
    @SuppressWarnings("unused")
    public String getNameCustomer(Customer customer){
        if(customer == null){
            throw new RuntimeException("Name of customer not found");
        }
        return customer.getName();
    }
    @SuppressWarnings("unused")
    public List<DetailSale> mapDetailSaleCreateDTOtoDetailSale(List<DetailSaleCreateDTO> detailsaleCreateDTO) {
        if ( detailsaleCreateDTO == null ) {
            throw new RuntimeException("The details of the sale must not be null");
        }
        List<DetailSale> detailSale = new ArrayList<>();
        DetailSale detailSaleEntity;

        for ( DetailSaleCreateDTO detailSaleCreateDTO : detailsaleCreateDTO ) {
            detailSaleEntity = new DetailSale();
            detailSaleEntity.setProduct(repositoryProduct.findById(detailSaleCreateDTO.product()).orElseThrow(()->new RuntimeException("Product not found")));
            detailSaleEntity.setQuantity(detailSaleCreateDTO.quantity());
            detailSale.add(detailSaleEntity);
        }

        return detailSale;
    }
}
