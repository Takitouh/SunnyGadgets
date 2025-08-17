package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameTotalSalary;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class RepositoryProviderTest {
    @Autowired
    private IRepositoryProvider repositoryProvider;

    private Provider provider;

    @BeforeEach
    void setUp() {
        provider = createProvider();
        repositoryProvider.save(provider);
    }

    @DisplayName(value = "Should return the name and salary of all providers")
    @Test
    void givenProvider_whenSaveProvider_thenReturnSalaryOfProvider() {
        List<NameTotalSalary> result = repositoryProvider.getProvidersSalary();
        assertNotNull(result);
        assertEquals(provider.getSalary(), result.get(0).salary());
        assertEquals(provider.getName(), result.get(0).name());
    }

    private Provider createProvider() {
        provider = new Provider(null, "Nvidia", "luis@gmail.com");
        provider.setName("Luis");
        provider.setSalary(36000L);
        provider.setPhoneNumber("3124156523");
        return provider;
    }
}
