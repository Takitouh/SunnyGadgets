package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategory implements IServiceCategory {
    @Override
    public ResponseEntity<Category> createCategory(Category category) {
        return null;
    }

    @Override
    public ResponseEntity<List<Category>> createCategory(List<Category> categories) {
        return null;
    }

    @Override
    public ResponseEntity<Category> getCategoryById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Category>> allCategories() {
        return null;
    }

    @Override
    public ResponseEntity<Category> updateCategory(Category category, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Category> deleteCategory(Long id) {
        return null;
    }
}
