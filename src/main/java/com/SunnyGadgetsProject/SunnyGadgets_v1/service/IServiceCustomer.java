package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CustomerCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CustomerResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface IServiceCustomer {
    CustomerResponseDTO createCustomer(CustomerCreateDTO userSunnyGadgets);
    List<CustomerResponseDTO> createCustomer(List<CustomerCreateDTO> userSunnyGadgetsCustomer);
    CustomerResponseDTO getCustomerById(Long id);
    List<CustomerResponseDTO> allCustomers();
    Customer updateCustomer(Customer userSunnyGadgetsCustomer, Long id);
    void deleteCustomer(Long id);

}
