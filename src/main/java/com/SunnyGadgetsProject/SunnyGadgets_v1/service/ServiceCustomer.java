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
import java.util.Optional;

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
        logger.info("Customer created: {}", customer.getName());
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
    public CustomerResponseDTO updateCustomer(CustomerCreateDTO customer, Long id) {
        Optional<Customer> optionalCustomer = repositoryCustomer.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new EntityNotFoundException("Customer with id " + id + " not found"); //Exception Not found
        }


        optionalCustomer.get().setName(customer.getName());
        optionalCustomer.get().setEmail(customer.getEmail());
        optionalCustomer.get().setAddress(customer.getAddress());
        optionalCustomer.get().setPhoneNumber(customer.getPhoneNumber());
        optionalCustomer.get().setAge(customer.getAge());


        repositoryCustomer.save(optionalCustomer.get());

        logger.info("Customer updated: {}", optionalCustomer.get().getName());

        return customerMapper.toDto(optionalCustomer.get());
    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> customer = repositoryCustomer.findById(id);
        if (customer.isEmpty()) {
            throw new EntityNotFoundException("Customer with id " + id + " not found"); //Exception not found
        }
        repositoryCustomer.deleteById(id);
        logger.info("Customer deleted: {}", customer.get().getEmail());
    }

    @Override
    public List<NameCustomerDTO> findCustomersByAgeGreaterThanEqual(Integer age) {
        if (age <= 0) {
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