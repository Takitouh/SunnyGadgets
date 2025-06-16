package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Employee;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceEmployee;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<Employee> employeeOptional = serviceEmployee.getEmployeeById(id);
        if (employeeOptional.isEmpty()){
            throw new EntityNotFoundException("Employee with ID " + id + " not found");
        }
        return ResponseEntity.ok(employeeOptional.get());
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> employees = serviceEmployee.allEmployee();
        if (employees.isEmpty()){
            throw new EntityNotFoundException("Employee list is empty");
        }
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        serviceEmployee.createEmployee(employee);

        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<Employee>> createEmployee(@RequestBody List<Employee> employees) {
        serviceEmployee.createEmployee(employees);

        return new ResponseEntity<>(employees, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
        serviceEmployee.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        serviceEmployee.updateEmployee(employee, id);
        return ResponseEntity.ok(employee);
    }
}
