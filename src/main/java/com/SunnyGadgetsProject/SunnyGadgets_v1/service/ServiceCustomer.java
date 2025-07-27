package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.CustomerMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.SaleMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryCustomer;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ServiceCustomer implements IServiceCustomer {

    private static final Logger logger = LoggerFactory.getLogger(ServiceCustomer.class);
    private final IRepositoryCustomer repositoryCustomer;
    private final CustomerMapper customerMapper;
    private final SaleMapper saleMapper;


    public ServiceCustomer(IRepositoryCustomer repositoryCustomer, CustomerMapper customerMapper, SaleMapper saleMapper) {
        this.repositoryCustomer = repositoryCustomer;
        this.customerMapper = customerMapper;
        this.saleMapper = saleMapper;
    }

    @Override
    public CustomerResponseDTO createCustomer(CustomerCreateDTO customer) {
        Customer cu = customerMapper.toEntity(customer);
        cu.setPurchases(new HashSet<>());
        cu = repositoryCustomer.save(cu);
        CustomerResponseDTO responseDTO = customerMapper.toDto(cu);
        logger.info("Customer created: {}", customer.name());
        return responseDTO;
    }

    @Override
    public List<CustomerResponseDTO> createCustomer(List<CustomerCreateDTO> customers) {
        List<Customer> customerList = new ArrayList<>();
        List<CustomerResponseDTO> customerDTOList = new ArrayList<>();
        Customer customer;
        for (CustomerCreateDTO cu : customers) {
            customer = customerMapper.toEntity(cu);
            customer.setPurchases(new HashSet<>());
            customerList.add(customer);
        }
        repositoryCustomer.saveAll(customerList);
        for (Customer cu : customerList) {
            customerDTOList.add(customerMapper.toDto(cu));
        }
        logger.info("Customer's created: {}", customers);
        return customerDTOList;
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = repositoryCustomer.findById(id).orElseThrow(EntityNotFoundException::new);
        return customerMapper.toDto(customer);
    }

    @Override
    public List<CustomerResponseDTO> allCustomers() {
        List<CustomerResponseDTO> customers = new ArrayList<>();
        for (Customer c : repositoryCustomer.findAll()) {
            customers.add(customerMapper.toDto(c));
        }
        if (customers.isEmpty()) {
            throw new EntityNotFoundException("No customer's found"); //Exception Not Found
        }
        return customers;
    }

    @Override
    public CustomerResponseDTO updateCustomer(CustomerPutDTO customer, Long id) {
        Customer optionalCustomer = repositoryCustomer.findById(id).orElseThrow(EntityNotFoundException::new);

        optionalCustomer.setName(customer.name());
        optionalCustomer.setEmail(customer.email());
        optionalCustomer.setAddress(customer.address());
        optionalCustomer.setPhoneNumber(customer.phoneNumber());
        optionalCustomer.setAge(customer.age());

        repositoryCustomer.save(optionalCustomer);

        logger.info("Customer updated with PUT: {}", optionalCustomer.getName());

        return customerMapper.toDto(optionalCustomer);
    }

    @Override
    public CustomerResponseDTO updateCustomer(CustomerPatchDTO customerPatchDTO, Long id) {
        Customer customerEntity = repositoryCustomer.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found"));


        //We check if the values sent are empty or null, if it is, so we put the previous value else the new value
        customerEntity.setName(customerPatchDTO.name() == null || customerPatchDTO.name().isEmpty() ? customerEntity.getName() : customerPatchDTO.name());
        customerEntity.setEmail(customerPatchDTO.email() == null || customerPatchDTO.email().isEmpty() ? customerEntity.getEmail() : customerPatchDTO.email());
        customerEntity.setAddress(customerPatchDTO.address() == null || customerPatchDTO.address().isEmpty() ? customerEntity.getAddress() : customerPatchDTO.address());
        customerEntity.setPhoneNumber(customerPatchDTO.phoneNumber() == null || customerPatchDTO.phoneNumber().isEmpty() ? customerEntity.getPhoneNumber() : customerPatchDTO.phoneNumber());
        customerEntity.setAge(customerPatchDTO.age() == null ? customerEntity.getAge() : customerPatchDTO.age());


        repositoryCustomer.save(customerEntity);

        logger.info("Customer updated with PATCH: {}", customerEntity.getName());

        return customerMapper.toDto(customerEntity);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = repositoryCustomer.findById(id).orElseThrow(EntityNotFoundException::new);

        repositoryCustomer.deleteById(id);
        logger.info("Customer deleted: {}", customer.getName());
    }

    @Override
    public List<NameCustomerDTO> findCustomersByAgeGreaterThanEqual(Integer age) {
        if (age < 16) {
            throw new EntityNotFoundException("Invalid age: " + age);
        }
        return repositoryCustomer.findCustomersByAgeGreaterThanEqual(age);
    }

    @Override
    public List<SaleResponseDTO> findPurchaseCustomers(Long id) {
        List<SaleResponseDTO> purchasesCustomer = new ArrayList<>();
        for(Sale s : repositoryCustomer.findPurchaseCustomers(id)){
           purchasesCustomer.add(saleMapper.toDto(s));
        }
        if (purchasesCustomer.isEmpty()) {
            throw new EntityNotFoundException("No purchases found for customer with id " + id);
        }
        return purchasesCustomer;
    }
}
