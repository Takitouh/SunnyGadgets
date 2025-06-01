package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Employee;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Employee> createEmployee(Employee employee) {
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repositoryEmployee.save(employee);
        logger.info("Employee created: {}", employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Employee>> createEmployee(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repositoryEmployee.saveAll(employees);
        logger.info("Employee's created: {}", employees);
        return new ResponseEntity<>(employees, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(Long id) {
        Optional<Employee> employee = repositoryEmployee.findById(id);

        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<Employee>> allEmployee() {
        List<Employee> employees = repositoryEmployee.findAll();
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> updateEmployee(Employee employee, Long id) {
        Optional<Employee> employeeOptional = repositoryEmployee.findById(id);
        if (employeeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Actualiza los campos necesarios:
        employeeOptional.get().setSalary(employee.getSalary());
        employeeOptional.get().setName(employee.getName());
        employeeOptional.get().setEmail(employee.getEmail());
        //Aca hay que revisar como se actualiza la fecha de modificacion
        employeeOptional.get().setModificationDate(employee.getModificationDate());


        repositoryEmployee.save(employeeOptional.get());
        logger.info("Employee updated: {}",  employeeOptional.get());
        return new ResponseEntity<>(employeeOptional.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> deleteEmployee(Long id) {
        Optional<Employee> employeeOptional = repositoryEmployee.findById(id);
        if (employeeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Employee deleted: {}", employeeOptional.get());
        repositoryEmployee.deleteById(id);
        return new ResponseEntity<>(employeeOptional.get(), HttpStatus.OK);
    }
}
