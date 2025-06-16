package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositorySeller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public Seller createSeller(Seller seller) {
        repositorySeller.save(seller);
        logger.info("Seller created: {}", seller);
        return seller;
    }

    @Override
    public List<Seller> createSeller(List<Seller> sellers) {
        repositorySeller.saveAll(sellers);
        logger.info("Sellers created: {}", sellers);
        return sellers;
    }

    @Override
    public Optional<Seller> getSellerById(Long id) {
        return repositorySeller.findById(id);
    }

    @Override
    public List<Seller> allSellers() {
        List<Seller> sellers = repositorySeller.findAll();
        if (sellers.isEmpty()) {
            return null; //Exception not found
        }
        return sellers;
    }

    @Override
    public Seller updateSeller(Seller seller, Long id) {
        Optional<Seller> sellerOptional = repositorySeller.findById(id);
        if (sellerOptional.isEmpty()) {
            return null; //Exception not found
        }

        sellerOptional.get().setName(seller.getName());
        sellerOptional.get().setEmail(seller.getEmail());
        sellerOptional.get().setPhone(seller.getPhone());
        sellerOptional.get().setSales(seller.getSales());
        sellerOptional.get().setSalary(seller.getSalary());

        repositorySeller.save(sellerOptional.get());
        logger.info("Seller updated: {}", seller);
        return seller;
    }

    @Override
    public void deleteSeller(Long id) {
        Optional<Seller> sellerOptional = repositorySeller.findById(id);
        if (sellerOptional.isEmpty()) {
            return; //Exception not found
        }
        logger.info("Seller deleted: {}", sellerOptional.get());
        repositorySeller.deleteById(id);
    }
}