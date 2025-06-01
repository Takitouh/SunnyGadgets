package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceSeller {
    ResponseEntity<Seller> createSeller(Seller seller);
    ResponseEntity<List<Seller>> createSeller(List<Seller> sellers);
    ResponseEntity<Seller> getSellerById(Long id);
    ResponseEntity<List<Seller>> allSellers();
    ResponseEntity<Seller> updateSeller(Seller seller, Long id);
    ResponseEntity<Seller> deleteSeller(Long id);

}
