package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameDescriptionPriceProductDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class RepositoryProductTest {
    @Autowired
    private IRepositoryProduct repositoryProduct;

    @Autowired
    private IRepositoryCategory repositoryCategory;

    private Product product;

    @BeforeEach
    void setup(){
        Category category = new Category(null, "Peripheries", "Description of peripheries", null);
        repositoryCategory.save(category);
        product = new Product(null, "Mouse RGB", "Description of mouse RGB", 150, 35, category, null);
        repositoryProduct.save(product);
    }
    @DisplayName(value = "Should return products by theirs price greater or equal to input price")
    @Test
    void givenPrice_whenFindProductByPriceGreaterOrEqual_thenReturnProducts(){
       long price = 120;
       List<NameDescriptionPriceProductDTO> result = repositoryProduct.findByPriceGreaterThanEqual(price);

       assertNotNull(result);
       assertEquals(product.getPrice(), result.get(0).price());
       assertEquals(product.getName(), result.get(0).name());
    }
    @DisplayName(value = "Should return products by theirs category name is equal to input category name")
    @Test
    void givenNameCategory_whenFindProductByCategory_thenReturnProducts(){
        String categoryName = "Peripheries";
        List<NameDescriptionPriceProductDTO> result = repositoryProduct.findByCategory(categoryName);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(product.getName(), result.get(0).name());
    }
}
