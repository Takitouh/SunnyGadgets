package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SellerCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SellerResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.SellerMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositorySeller;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceSeller implements IServiceSeller {

    private static final Logger logger = LoggerFactory.getLogger(ServiceSeller.class);
    private final IRepositorySeller repositorySeller;
    private final SellerMapper sellerMapper;

    public ServiceSeller(IRepositorySeller repositorySeller, SellerMapper sellerMapper) {
        this.repositorySeller = repositorySeller;
        this.sellerMapper = sellerMapper;
    }

    @Override
    public SellerResponseDTO createSeller(SellerCreateDTO seller) {
        Seller s = sellerMapper.toEntity(seller);
        s = repositorySeller.save(s);
        SellerResponseDTO responseDTO = sellerMapper.toDto(s);
        logger.info("Seller created: {}", seller);
        return responseDTO;
    }

    @Override
    public List<SellerResponseDTO> createSeller(List<SellerCreateDTO> sellers) {
        List<Seller> sellerList = new ArrayList<>();
        List<SellerResponseDTO> sellerDTOList = new ArrayList<>();
        for (SellerCreateDTO seller : sellers) {
            sellerList.add(sellerMapper.toEntity(seller));
        }
        repositorySeller.saveAll(sellerList);
        for (Seller s : sellerList) {
            sellerDTOList.add(sellerMapper.toDto(s));
        }
        logger.info("Sellers created: {}", sellers);
        return sellerDTOList;
    }

    @Override
    public SellerResponseDTO getSellerById(Long id) {
        Seller seller = repositorySeller.findById(id).orElseThrow(EntityNotFoundException::new);
        return sellerMapper.toDto(seller);
    }

    @Override
    public List<SellerResponseDTO> allSellers() {
        List<SellerResponseDTO> sellers = new ArrayList<>();
        for (Seller s : repositorySeller.findAll()) {
            sellers.add(sellerMapper.toDto(s));
        }
        if (sellers.isEmpty()) {
            throw new EntityNotFoundException("No sellers found"); //Excepcion Not Found
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
        sellerOptional.get().setPhoneNumber(seller.getPhoneNumber());
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
            throw new EntityNotFoundException("Seller with ID " + id + " not found"); //Exception not found
        }
        logger.info("Seller deleted: {}", sellerOptional.get());
        repositorySeller.deleteById(id);
    }
}