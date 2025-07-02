package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Employee;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmployee implements IServiceEmployee {

    private static final Logger logger = LoggerFactory.getLogger(ServiceEmployee.class);

    private final IRepositoryEmployee repositoryEmployee;

    public ServiceEmployee(IRepositoryEmployee repositoryEmployee) {
        this.repositoryEmployee = repositoryEmployee;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        repositoryEmployee.save(employee);
        logger.info("Employee created: {}", employee);
        return employee;
    }

    @Override
    public List<Employee> createEmployee(List<Employee> employees) {
        repositoryEmployee.saveAll(employees);
        logger.info("Employee's created: {}", employees);
        return employees;
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return repositoryEmployee.findById(id);
    }

    @Override
    public List<Employee> allEmployee() {
        List<Employee> employees = repositoryEmployee.findAll();
        if (employees.isEmpty()) {
            return null; //Exception not found
        }
        return employees;
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        Optional<Employee> employeeOptional = repositoryEmployee.findById(id);
        if (employeeOptional.isEmpty()) {
            return null; //Exception not found
        }


        employeeOptional.get().setSalary(employee.getSalary());
        employeeOptional.get().setName(employee.getName());
        employeeOptional.get().setPhoneNumber(employee.getPhoneNumber());
        //Aca hay que revisar como se actualiza la fecha de modificacion
        //Testear
        employeeOptional.get().setUpdatedAt(employee.getUpdatedAt());


        repositoryEmployee.save(employeeOptional.get());
        logger.info("Employee updated: {}",  employeeOptional.get());
        return employeeOptional.get();
    }

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> employeeOptional = repositoryEmployee.findById(id);
        if (employeeOptional.isEmpty()) {
            return ; //Exception not found
        }
        logger.info("Employee deleted: {}", employeeOptional.get());
        repositoryEmployee.deleteById(id);
    }
}
