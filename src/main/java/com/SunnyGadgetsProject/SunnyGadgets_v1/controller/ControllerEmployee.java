package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

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
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return serviceEmployee.getEmployeeById(id);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return serviceEmployee.allEmployee();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return serviceEmployee.createEmployee(employee);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<Employee>> createEmployee(@RequestBody List<Employee> employees) {
        return serviceEmployee.createEmployee(employees);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
        return serviceEmployee.deleteEmployee(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        return serviceEmployee.updateEmployee(employee, id);
    }
}
