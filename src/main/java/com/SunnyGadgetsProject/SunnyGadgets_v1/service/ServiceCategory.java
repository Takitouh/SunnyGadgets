package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.CategoryMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryCategory;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProduct;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceCategory implements IServiceCategory {

    private static final Logger logger = LoggerFactory.getLogger(ServiceCategory.class);
    private final CategoryMapper categoryMapper;

    //I will use the constructor method for DI because I read it's a better practice
    private final IRepositoryCategory repositoryCategory;
    private final IRepositoryProduct repositoryProduct;

    public ServiceCategory(CategoryMapper categoryMapper, IRepositoryCategory repositoryCategory, IRepositoryProduct repositoryProduct) {
        this.categoryMapper = categoryMapper;
        this.repositoryCategory = repositoryCategory;
        this.repositoryProduct = repositoryProduct;
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryCreateDTO category) {
        Category c = categoryMapper.toEntity(category);
        c = repositoryCategory.save(c);
        CategoryResponseDTO responseDTO = categoryMapper.toDto(c);
        logger.info("Category created: {}", c);
        return responseDTO;
    }

    @Override
    public List<CategoryResponseDTO> createCategory(List<CategoryCreateDTO> categories) {
        List<Category> categoryList = new ArrayList<>();
        List<CategoryResponseDTO> categoryDTOList = new ArrayList<>();
        for (CategoryCreateDTO category : categories) {
            categoryList.add(categoryMapper.toEntity(category));
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
        Category category = repositoryCategory.findById(id).orElseThrow(EntityNotFoundException::new);

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
    public CategoryResponseDTO updateCategory(CategoryCreateDTO category, Long id) {
        Optional<Category> categoryOptional = repositoryCategory.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new EntityNotFoundException("Category with id " + id + " not found"); //Exception Not Found
        }

        Set<Product> productSet = new HashSet<>();
        Product product;
        for(Long idProduct : category.getExistingProductIds()){
            product = repositoryProduct.findById(idProduct).orElseThrow(EntityNotFoundException::new);
            productSet.add(product);
        }

        categoryOptional.get().setName(category.getName());
        categoryOptional.get().setDescription(category.getDescription());
        categoryOptional.get().setProductSet(productSet);
        repositoryCategory.save(categoryOptional.get());

        logger.info("Category updated: {}", category);

        return categoryMapper.toDto(categoryOptional.get());
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
