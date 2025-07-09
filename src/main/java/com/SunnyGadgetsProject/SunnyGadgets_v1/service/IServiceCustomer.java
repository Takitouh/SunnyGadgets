package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CustomerCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CustomerResponseDTO;

import java.util.List;

public interface IServiceCustomer {
    CustomerResponseDTO createCustomer(CustomerCreateDTO userSunnyGadgets);
    List<CustomerResponseDTO> createCustomer(List<CustomerCreateDTO> userSunnyGadgetsCustomer);
    CustomerResponseDTO getCustomerById(Long id);
    List<CustomerResponseDTO> allCustomers();
    CustomerResponseDTO updateCustomer(CustomerCreateDTO userSunnyGadgetsCustomer, Long id);
    void deleteCustomer(Long id);

}
