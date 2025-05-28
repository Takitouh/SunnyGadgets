package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceCategory {
    ResponseEntity<Category> createCategory(Category category);
    ResponseEntity<List<Category>> createCategory(List<Category> categories);
    ResponseEntity<Category> getCategoryById(Long id);
    ResponseEntity<List<Category>> allCategories();
    ResponseEntity<Category> updateCategory(Category category, Long id);
    ResponseEntity<Category> deleteCategory(Long id);

}
