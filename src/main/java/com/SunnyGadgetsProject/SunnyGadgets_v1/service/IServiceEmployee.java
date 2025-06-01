package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceEmployee {
    ResponseEntity<Employee> createEmployee(Employee employee);
    ResponseEntity<List<Employee>> createEmployee(List<Employee> employees);
    ResponseEntity<Employee> getEmployeeById(Long id);
    ResponseEntity<List<Employee>> allEmployee();
    ResponseEntity<Employee> updateEmployee(Employee employee, Long id);
    ResponseEntity<Employee> deleteEmployee(Long id);
}
