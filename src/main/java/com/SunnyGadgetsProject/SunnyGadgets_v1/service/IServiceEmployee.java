package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface IServiceEmployee {
    Employee createEmployee(Employee employee);
    List<Employee> createEmployee(List<Employee> employees);
    Optional<Employee> getEmployeeById(Long id);
    List<Employee> allEmployee();
    Employee updateEmployee(Employee employee, Long id);
    void deleteEmployee(Long id);
}
