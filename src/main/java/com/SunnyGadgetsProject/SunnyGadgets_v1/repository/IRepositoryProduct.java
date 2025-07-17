package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameDescriptionPriceProductDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepositoryProduct extends JpaRepository<Product, Long> {
    //Query's
    List<NameDescriptionPriceProductDTO> findByPriceGreaterThanEqual(long price);
    @Query(value = "SELECT p.name, p.description, p.price FROM products p INNER JOIN categories c ON c.id_category = p.category_id_category WHERE UPPER(c.name) = UPPER(:category)"
            ,nativeQuery = true)
    List<NameDescriptionPriceProductDTO> findByCategory(@Param("category") String category);
}
