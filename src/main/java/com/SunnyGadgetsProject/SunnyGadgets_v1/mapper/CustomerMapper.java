package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CustomerCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CustomerResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CustomerMapper {
    // CreateDTO → Entity
    Customer toEntity(CustomerCreateDTO dto);

    // Entity → ResponseDTO
    CustomerResponseDTO toDto(Customer customer);
}
