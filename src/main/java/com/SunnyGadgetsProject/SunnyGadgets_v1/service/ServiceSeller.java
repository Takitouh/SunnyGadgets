package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceSeller implements IServiceSeller {
    @Override
    public ResponseEntity<Seller> createSeller(Seller seller) {
        return null;
    }

    @Override
    public ResponseEntity<List<Seller>> createSeller(List<Seller> sellers) {
        return null;
    }

    @Override
    public ResponseEntity<Seller> getSellerById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Seller>> allSellers() {
        return null;
    }

    @Override
    public ResponseEntity<Seller> updateSeller(Seller seller, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Seller> deleteSeller(Long id) {
        return null;
    }
}
