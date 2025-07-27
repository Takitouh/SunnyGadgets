package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;

import java.util.List;

public interface IServiceCustomer {
    CustomerResponseDTO createCustomer(CustomerCreateDTO userSunnyGadgets);
    List<CustomerResponseDTO> createCustomer(List<CustomerCreateDTO> userSunnyGadgetsCustomer);
    CustomerResponseDTO getCustomerById(Long id);
    List<CustomerResponseDTO> allCustomers();
    CustomerResponseDTO updateCustomer(CustomerPutDTO userSunnyGadgetsCustomer, Long id);
    CustomerResponseDTO updateCustomer(CustomerPatchDTO userSunnyGadgetsCustomer, Long id);
    void deleteCustomer(Long id);
    //Query's
    List<NameCustomerDTO> findCustomersByAgeGreaterThanEqual(Integer age);
    List<SaleResponseDTO> findPurchaseCustomers(Long id);
}
