package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryProduct extends JpaRepository<Product, Long> {
}
