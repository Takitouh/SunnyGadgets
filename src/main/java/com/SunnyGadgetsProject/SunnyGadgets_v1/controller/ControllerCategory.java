package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;


import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceCategory;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/category")
public class ControllerCategory {
    private final IServiceCategory serviceCategory;

    public ControllerCategory(IServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = serviceCategory.getCategoryById(id);
        if (categoryOptional.isEmpty()){
            throw new EntityNotFoundException("Category with ID " + id + " not found");
        }
        return ResponseEntity.ok(categoryOptional.get());    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = serviceCategory.allCategories();
        if (categories.isEmpty()){
            throw new EntityNotFoundException("Category list is empty");
        }
        return ResponseEntity.ok(categories);    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        serviceCategory.createCategory(category);

        return new ResponseEntity<>(category, HttpStatus.CREATED);    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<Category>> createCategories(@RequestBody List<Category> categories) {
        serviceCategory.createCategory(categories);

        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        serviceCategory.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        serviceCategory.updateCategory(category, id);
        return ResponseEntity.ok(category);
    }
}

