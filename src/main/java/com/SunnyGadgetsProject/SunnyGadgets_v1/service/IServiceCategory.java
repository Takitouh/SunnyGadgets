package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryResponseDTO;

import java.util.List;

public interface IServiceCategory {
    CategoryResponseDTO createCategory(CategoryCreateDTO category);
    List<CategoryResponseDTO> createCategory(List<CategoryCreateDTO> categories);
    CategoryResponseDTO getCategoryById(Long id);
    List<CategoryResponseDTO> allCategories();
    CategoryResponseDTO updateCategory(CategoryCreateDTO category, Long id);
    void deleteCategory(Long id);

}
