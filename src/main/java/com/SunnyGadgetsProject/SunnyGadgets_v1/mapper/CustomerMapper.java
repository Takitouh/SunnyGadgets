package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CustomerCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CustomerResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SaleMapper.class})

public interface CustomerMapper {
    // CreateDTO → Entity
    @Mapping(target = "idCustomer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "purchases", ignore = true)
    Customer toEntity(CustomerCreateDTO dto);

    // Entity → ResponseDTO
    //@Mapping(target = "sales", ignore = true)
    CustomerResponseDTO toDto(Customer customer);

}
