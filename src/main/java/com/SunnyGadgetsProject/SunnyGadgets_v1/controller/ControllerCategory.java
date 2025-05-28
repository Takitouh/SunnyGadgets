package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;


import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return serviceCategory.getCategoryById(id);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Category>> getAllCategories() {
        return serviceCategory.allCategories();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return serviceCategory.createCategory(category);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<Category>> createCategories(@RequestBody List<Category> categories) {
        return serviceCategory.createCategory(categories);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        return serviceCategory.deleteCategory(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        return serviceCategory.updateCategory(category, id);
    }
}

