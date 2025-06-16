package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;

import java.util.List;
import java.util.Optional;

public interface IServiceCategory {
    Category createCategory(Category category);
    List<Category> createCategory(List<Category> categories);
    Optional<Category> getCategoryById(Long id);
    List<Category> allCategories();
    Category updateCategory(Category category, Long id);
    void deleteCategory(Long id);

}
