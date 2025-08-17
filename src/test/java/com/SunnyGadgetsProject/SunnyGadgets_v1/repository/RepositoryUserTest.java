package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.UserSec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = IRepositoryUserSec.class))
public class RepositoryUserTest {

    @Autowired
    private IRepositoryUserSec repositoryUserSec;

    private UserSec userSec;

    @BeforeEach
    void setUp() {
        userSec = new UserSec(null, "Robert", "123", true, true, true, true, new HashSet<>());
        repositoryUserSec.save(userSec);
    }

    @DisplayName(value = "Should search the user by username and return it")
    @Test
    void givenString_whenFindUserEntityByUsername_thenReturnUserSec() {
        Optional<UserSec> result = repositoryUserSec.findUserEntityByUsername(userSec.getUsername());
        assertTrue(result.isPresent());
        assertEquals(userSec, result.get());
    }

    @DisplayName(value = "Should return empty when username does not exist")
    @Test
    void givenString_whenFindUserEntityByUsername_thenReturnEmptyOptionalUserSec() {
        Optional<UserSec> result = repositoryUserSec.findUserEntityByUsername("NonExistentUsername");
        assertTrue(result.isEmpty());
    }
}
