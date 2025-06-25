package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryCategory;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCategory implements IServiceCategory {

    private static final Logger logger = LoggerFactory.getLogger(ServiceCategory.class);

    //I will use the constructor method for DI because I read it's a better practice
    IRepositoryCategory repositoryCategory;

    public ServiceCategory(IRepositoryCategory repositoryCategory) {
        this.repositoryCategory = repositoryCategory;
    }

    @Override
    public Category createCategory(Category category) {

         repositoryCategory.save(category);
        logger.info("Category created: {}", category);
        return category;
    }

    @Override
    public List<Category> createCategory(List<Category> categories) {

        repositoryCategory.saveAll(categories);
        logger.info("Category's created: {}", categories);
        return categories;
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        Optional<Category> category = repositoryCategory.findById(id);
        if (category.isEmpty()) {
            throw new EntityNotFoundException("Category with id " + id + " not found"); //Excepcion Not Found
        }
        return category;

    }

    @Override
    public List<Category> allCategories() {
        List<Category> categories = repositoryCategory.findAll();
        if (categories.isEmpty()) {
            throw new EntityNotFoundException("No categories found"); //Excepcion Not Found
        }
        return categories;
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        Optional<Category> categoryOptional = repositoryCategory.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new EntityNotFoundException("Category with id " + id + " not found"); //Excepcion Not Found
        }
        categoryOptional.get().setName(category.getName());
        categoryOptional.get().setDescription(category.getDescription());
        categoryOptional.get().setProductSet(category.getProductSet());
        repositoryCategory.save(categoryOptional.get());

        logger.info("Category updated: {}", category);
        return category;
    }

    @Override
    public void deleteCategory(Long id) {

        Optional<Category> categoryOptional = repositoryCategory.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new EntityNotFoundException("Category with id " + id + " not found") ; // Excepcion not found
        }
        logger.info("Category deleted: {}", categoryOptional.get());
        repositoryCategory.deleteById(id);
    }
}
