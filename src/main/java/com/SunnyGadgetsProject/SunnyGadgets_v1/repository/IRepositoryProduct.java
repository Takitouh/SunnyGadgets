package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameDescriptionPriceProductDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProductResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepositoryProduct extends JpaRepository<Product, Long> {
    //Query's
    List<NameDescriptionPriceProductDTO> findByPriceGreaterThanEqual(long price);
}
