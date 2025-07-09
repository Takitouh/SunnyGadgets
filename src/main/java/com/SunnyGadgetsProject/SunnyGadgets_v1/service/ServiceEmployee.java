package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.EmployeeResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Employee;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.EmployeeMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryEmployee;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmployee implements IServiceEmployee {

    private static final Logger logger = LoggerFactory.getLogger(ServiceEmployee.class);

    private final IRepositoryEmployee repositoryEmployee;
    private final EmployeeMapper employeeMapper;

    public ServiceEmployee(IRepositoryEmployee repositoryEmployee, EmployeeMapper employeeMapper) {
        this.repositoryEmployee = repositoryEmployee;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        return employeeMapper.toDto(repositoryEmployee.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<EmployeeResponseDTO> allEmployee() {
        List<EmployeeResponseDTO> employeeResponseDTOS = new ArrayList<>();
        for (Employee e : repositoryEmployee.findAll()) {
            employeeResponseDTOS.add(employeeMapper.toDto(e));
        }
        if (employeeResponseDTOS.isEmpty()) {
            throw new EntityNotFoundException("No employee's found"); //Excepcion Not Found
        }
        return employeeResponseDTOS;
    }



    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> employeeOptional = repositoryEmployee.findById(id);
        if (employeeOptional.isEmpty()) {
            throw new EntityNotFoundException("Employee with ID " + id + " not found") ; //Exception not found
        }
        logger.info("Employee deleted: {}", employeeOptional.get());
        repositoryEmployee.deleteById(id);
    }
}
