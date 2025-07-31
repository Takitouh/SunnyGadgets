package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryPatchDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryPutDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.CategoryMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryCategory;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceCategory implements IServiceCategory {

    private static final Logger logger = LoggerFactory.getLogger(ServiceCategory.class);
    private final CategoryMapper categoryMapper;
    private final IRepositoryCategory repositoryCategory;

    public ServiceCategory(CategoryMapper categoryMapper, IRepositoryCategory repositoryCategory) {
        this.categoryMapper = categoryMapper;
        this.repositoryCategory = repositoryCategory;
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryCreateDTO category) {
        Category c = categoryMapper.toEntity(category);
        c.setProductSet(new HashSet<>());
        c = repositoryCategory.save(c);
        CategoryResponseDTO responseDTO = categoryMapper.toDto(c);
        logger.info("Category created: {}", c);
        return responseDTO;
    }

    @Override
    public List<CategoryResponseDTO> createCategory(List<CategoryCreateDTO> categories) {
        List<Category> categoryList = new ArrayList<>();
        List<CategoryResponseDTO> categoryDTOList = new ArrayList<>();
        Category categ;
        for (CategoryCreateDTO category : categories) {
            categ = categoryMapper.toEntity(category);
            categ.setProductSet(new HashSet<>());
            categoryList.add(categ);
        }
        repositoryCategory.saveAll(categoryList);
        for (Category c : categoryList) {
            categoryDTOList.add(categoryMapper.toDto(c));
        }

        logger.info("Category's created: {}", categories);
        return categoryDTOList;
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = repositoryCategory.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));

        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryResponseDTO> allCategories() {
        List<CategoryResponseDTO> categories = new ArrayList<>();
        for (Category c : repositoryCategory.findAll()) {
            categories.add(categoryMapper.toDto(c));
        }
        if (categories.isEmpty()) {
            throw new EntityNotFoundException("No categories found"); //Excepcion Not Found
        }
        return categories;
    }

    @Override
    public CategoryResponseDTO updateCategory(CategoryPutDTO category, Long id) {
        Optional<Category> categoryOptional = repositoryCategory.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new EntityNotFoundException("Category with id " + id + " not found"); //Exception Not Found
        }

        categoryOptional.get().setName(category.name());
        categoryOptional.get().setDescription(category.description());
        repositoryCategory.save(categoryOptional.get());

        logger.info("Category updated with PUT: {}", category);

        return categoryMapper.toDto(categoryOptional.get());
    }

    @Override
    public CategoryResponseDTO updateCategory(CategoryPatchDTO category, Long id) {
        Category categoryEntity = repositoryCategory.findById(id).orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found"));

        String name = category.name() == null || category.name().isEmpty() ? categoryEntity.getName() : category.name();
        String description = category.description() == null || category.description().isEmpty() ? categoryEntity.getDescription() : category.description();


        categoryEntity.setName(name);
        categoryEntity.setDescription(description);
        repositoryCategory.save(categoryEntity);

        logger.info("Category updated with PATCH: {}", category);
        return categoryMapper.toDto(categoryEntity);
    }

    @Override
    public void deleteCategory(Long id) {

        Optional<Category> categoryOptional = repositoryCategory.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new EntityNotFoundException("Category with id " + id + " not found"); // Excepcion not found
        }
        logger.info("Category deleted: {}", categoryOptional.get());
        repositoryCategory.deleteById(id);
    }
}
