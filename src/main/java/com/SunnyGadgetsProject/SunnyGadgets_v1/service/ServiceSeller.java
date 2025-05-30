package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositorySeller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceSeller implements IServiceSeller {

    private static final Logger logger = LoggerFactory.getLogger(ServiceSeller.class);
    private final IRepositorySeller repositorySeller;

    public ServiceSeller(IRepositorySeller repositorySeller) {
        this.repositorySeller = repositorySeller;
    }

    @Override
    public ResponseEntity<Seller> createSeller(Seller seller) {
        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repositorySeller.save(seller);
        logger.info("Seller created: {}", seller);
        return new ResponseEntity<>(seller, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Seller>> createSeller(List<Seller> sellers) {
        if (sellers == null || sellers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repositorySeller.saveAll(sellers);
        logger.info("Sellers created: {}", sellers);
        return new ResponseEntity<>(sellers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Seller> getSellerById(Long id) {
        Optional<Seller> seller = repositorySeller.findById(id);
        return seller.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<Seller>> allSellers() {
        List<Seller> sellers = repositorySeller.findAll();
        if (sellers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Seller> updateSeller(Seller seller, Long id) {
        Optional<Seller> sellerOptional = repositorySeller.findById(id);
        if (sellerOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Actualiza los campos necesarios:
        sellerOptional.get().setName(seller.getName());
        sellerOptional.get().setEmail(seller.getEmail());
        sellerOptional.get().setPhone(seller.getPhone());
        //We have to see if is necessary this because it should update automatically

        sellerOptional.get().setModificationDate(seller.getModificationDate());
        sellerOptional.get().setSales(seller.getSales());
        // Agrega otros campos según tu entidad Seller

        repositorySeller.save(sellerOptional.get());
        logger.info("Seller updated: {}", seller);
        return new ResponseEntity<>(sellerOptional.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Seller> deleteSeller(Long id) {
        Optional<Seller> sellerOptional = repositorySeller.findById(id);
        if (sellerOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Seller deleted: {}", sellerOptional.get());
        repositorySeller.deleteById(id);
        return new ResponseEntity<>(sellerOptional.get(), HttpStatus.OK);
    }
}