package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.EmployeeResponseDTO;

import java.util.List;

public interface IServiceEmployee {

    EmployeeResponseDTO getEmployeeById(Long id);
    List<EmployeeResponseDTO> allEmployee();
    void deleteEmployee(Long id);
}
