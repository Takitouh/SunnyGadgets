package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IServiceSeller {
    Seller createSeller(Seller seller);
    List<Seller> createSeller(List<Seller> sellers);
    Optional<Seller> getSellerById(Long id);
    List<Seller> allSellers();
    Seller updateSeller(Seller seller, Long id);
    void deleteSeller(Long id);

}
