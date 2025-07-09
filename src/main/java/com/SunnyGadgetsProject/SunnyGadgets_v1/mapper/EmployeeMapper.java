package com.SunnyGadgetsProject.SunnyGadgets_v1.mapper;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.EmployeeResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    // Entity → ResponseDTO
    EmployeeResponseDTO toDto(Employee employee);
}
