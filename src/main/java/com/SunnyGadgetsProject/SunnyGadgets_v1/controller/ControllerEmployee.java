package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.EmployeeResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Employee;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceEmployee;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/employee")
public class ControllerEmployee {
    private final IServiceEmployee serviceEmployee;

    public ControllerEmployee(IServiceEmployee serviceEmployee) {
        this.serviceEmployee = serviceEmployee;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(serviceEmployee.getEmployeeById(id));
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployee() {
        return ResponseEntity.ok(serviceEmployee.allEmployee());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
        serviceEmployee.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }


}
