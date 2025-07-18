package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameCustomerDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepositoryCustomer extends JpaRepository<Customer, Long> {
    //Query's
    List<NameCustomerDTO> findCustomersByAgeGreaterThanEqual(Integer age);

    @Query(value = "SELECT * FROM sales s, customers c  WHERE c.id_customer = s.id_sale AND :idCustomer = s.fk_customer ",
            nativeQuery = true)
    List<Sale> findPurchaseCustomers(@Param(value = "idCustomer") Long id);
}
