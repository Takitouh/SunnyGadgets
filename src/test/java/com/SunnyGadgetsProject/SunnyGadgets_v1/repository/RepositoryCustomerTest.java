package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameCustomerDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest()
public class RepositoryCustomerTest {
    @Autowired
    private IRepositoryCustomer repositoryCustomer;

    @Autowired
    private IRepositorySale repositorySale;

    @Autowired
    private IRepositorySeller repositorySeller;

    @Autowired
    private IRepositoryProduct repositoryProduct;

    @Autowired
    private IRepositoryCategory repositoryCategory;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer(null, 18, "Manhattan", "Michael", "michael@gmail.com", new HashSet<>(), "3144106253",
                new Timestamp(0), new Timestamp(0));
        repositoryCustomer.save(customer);
    }

    @Test
    @DisplayName(value = "Should found customer created and check if only 1 was created")
    void givenCustomer_whenSaved_thenCanBeFoundById() {
        Customer foundCustomer = repositoryCustomer.findById(customer.getIdCustomer()).orElse(null);

        assertNotNull(foundCustomer);
        assertEquals("Michael", foundCustomer.getName());
        assertEquals("michael@gmail.com", foundCustomer.getEmail());
        assertEquals(1, repositoryCustomer.count());
    }

    @Test
    @DisplayName(value = "Should find customer purchases by his ID")
    void withCustomerID_findPurchases() {
        Set<Sale> sales = createSaleAndDetailSaleWithCustomer();
        repositorySale.saveAll(sales);
        customer.setPurchases(sales);
        repositoryCustomer.save(customer);
        Set<Sale> purchases = repositoryCustomer.findPurchaseCustomers(customer.getIdCustomer());

        assertNotNull(purchases);
        assertEquals(1, purchases.size());
        assertEquals(customer.getPurchases(), purchases);
    }

    @Test
    @DisplayName(value = "Should find and show only the name of customer if their age's are equal or greater")
    void givenAge_FoundAllCustomersWithAgeGreaterThanEqual() {
        int age = 18;
        List<NameCustomerDTO> nameCustomers = repositoryCustomer.findCustomersByAgeGreaterThanEqual(age);

        assertNotNull(nameCustomers);
        assertEquals(customer.getName(), nameCustomers.get(0).name());
    }

    private Set<Sale> createSaleAndDetailSaleWithCustomer() {
        Seller seller = createSeller();
        Product product = createProduct();
        Sale sale = new Sale(null, null, 100, customer, seller, new ArrayList<>());
        sale.setListdetailSale(List.of(new DetailSale(null, 1, 100, 100,
                sale, product)));
        Set<Sale> sales = new HashSet<>();
        sales.add(sale);
        return sales;
    }

    private Seller createSeller() {
        Seller seller = new Seller(new HashSet<>(), 0L);
        seller.setName("Mauricio");
        seller.setSalary(15000L);
        seller.setPhoneNumber("3145102486");
        repositorySeller.save(seller);
        return seller;
    }

    private Product createProduct() {
        Category category = createCategory();
        Product product = new Product(null, "Iphone", "Iphone 13", 2500L, 8, category, new HashSet<>());
        repositoryProduct.save(product);
        return product;
    }

    private Category createCategory() {
        Category category = new Category(null, "Cellphones", "Category of cellphones", new HashSet<>());
        repositoryCategory.save(category);
        return category;
    }

}

