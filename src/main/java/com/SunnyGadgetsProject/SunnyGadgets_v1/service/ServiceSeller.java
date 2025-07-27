package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.SaleMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.SellerMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositorySeller;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceSeller implements IServiceSeller {

    private static final Logger logger = LoggerFactory.getLogger(ServiceSeller.class);
    private final IRepositorySeller repositorySeller;
    private final SellerMapper sellerMapper;
    private final SaleMapper saleMapper;

    public ServiceSeller(IRepositorySeller repositorySeller, SellerMapper sellerMapper, SaleMapper saleMapper) {
        this.repositorySeller = repositorySeller;
        this.sellerMapper = sellerMapper;
        this.saleMapper = saleMapper;
    }

    @Override
    public SellerResponseDTO createSeller(SellerCreateDTO seller) {
        Seller s = sellerMapper.toEntity(seller);
        s.setSales(new HashSet<>());
        s = repositorySeller.save(s);
        SellerResponseDTO responseDTO = sellerMapper.toDto(s);
        logger.info("Seller created: {}", seller);
        return responseDTO;
    }

    @Override
    public List<SellerResponseDTO> createSeller(List<SellerCreateDTO> sellers) {
        List<Seller> sellerList = new ArrayList<>();
        List<SellerResponseDTO> sellerDTOList = new ArrayList<>();
        Seller sellerEntity;
        for (SellerCreateDTO seller : sellers) {
            sellerEntity = sellerMapper.toEntity(seller);
            sellerEntity.setSales(new HashSet<>());
            sellerList.add(sellerEntity);
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
    public SellerResponseDTO updateSeller(SellerPutDTO sellerPutDTO, Long id) {
        Seller oldSeller = repositorySeller.findById(id).orElseThrow(() -> new EntityNotFoundException("Seller with id " + id + " not found"));

        Seller newSeller = sellerMapper.toEntity(sellerPutDTO);
        oldSeller.setName(newSeller.getName());
        oldSeller.setPhoneNumber(newSeller.getPhoneNumber());
        oldSeller.setSales(newSeller.getSales());
        oldSeller.setSalary(newSeller.getSalary());
        oldSeller.setCommission(newSeller.getCommission());

        repositorySeller.save(oldSeller);
        logger.info("Seller updated with PUT: {}", sellerPutDTO);
        return sellerMapper.toDto(oldSeller);
    }

    @Override
    public SellerResponseDTO updateSeller(SellerPatchDTO sellerPatchDTO, Long id) {
        Seller oldSeller = repositorySeller.findById(id).orElseThrow(() -> new EntityNotFoundException("Seller with id " + id + " not found"));

        Seller newSeller = sellerMapper.toEntity(sellerPatchDTO);
        oldSeller.setName(newSeller.getName() == null || newSeller.getName().isEmpty()? oldSeller.getName() : newSeller.getName());
        oldSeller.setPhoneNumber(newSeller.getPhoneNumber() == null || newSeller.getPhoneNumber().isEmpty()? oldSeller.getPhoneNumber() : newSeller.getPhoneNumber());
        oldSeller.setSales(newSeller.getSales() == null || newSeller.getSales().isEmpty()? oldSeller.getSales() : newSeller.getSales());
        oldSeller.setSalary(newSeller.getSalary() == null? oldSeller.getSalary() : newSeller.getSalary());
        oldSeller.setCommission(newSeller.getCommission() == 0? oldSeller.getCommission() : newSeller.getCommission());

        repositorySeller.save(oldSeller);
        logger.info("Seller updated with PATCH: {}", sellerPatchDTO);
        return sellerMapper.toDto(oldSeller);
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

    @Override
    public List<NameTotalSalarySeller> getSellersTotalSalary() {
        return repositorySeller.getSellersTotalSalary();
    }

    @Override
    public List<SaleResponseDTO> findSalesSeller(Long id) {
        List<SaleResponseDTO> salesSeller = new ArrayList<>();
        for(Sale s : repositorySeller.findSalesSeller(id)){
            salesSeller.add(saleMapper.toDto(s));
        }
        if (salesSeller.isEmpty()) {
            throw new EntityNotFoundException("No sales found for seller with id " + id);
        }
        return salesSeller;
    }
}