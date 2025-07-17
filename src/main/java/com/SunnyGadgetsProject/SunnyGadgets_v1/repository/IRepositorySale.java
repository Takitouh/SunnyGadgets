package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameQuantPurchasesCustomerDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepositorySale extends JpaRepository<Sale, Long> {
    //Query's
    //Find the total sold
    @Query(value = "SELECT SUM(s.total) AS Total FROM sales s"
    , nativeQuery = true)
    Long totalSales();
    //Find how many purchases have been done by customers
    @Query(value = "SELECT c.name, COUNT(s.id_sale) AS N_of_purchases FROM customers c, sales s WHERE c.id_customer = s.fk_customer\n" +
            "GROUP BY c.name;",
            nativeQuery = true)
    List<NameQuantPurchasesCustomerDTO> findCustomersByPurchases();
}
