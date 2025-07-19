package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameTotalSalarySeller;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepositorySeller extends JpaRepository<Seller, Long> {
    //Query's
    @Query(value = "SELECT e.name, e.salary+s.commission AS salary FROM sellers s INNER JOIN employes e ON e.id_employee = s.id_seller"
            , nativeQuery = true)
    List<NameTotalSalarySeller> getSellersTotalSalary();

    @Query(value = "SELECT * FROM sales s, sellers se  WHERE se.id_seller = s.id_sale AND :idSeller = s.fk_seller ",
            nativeQuery = true)
    List<Sale> findSalesSeller(@Param(value = "idSeller") Long id);
}
