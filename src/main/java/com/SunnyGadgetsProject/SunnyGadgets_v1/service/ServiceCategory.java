package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Category> createCategory(Category category) {
         if (category == null) {
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
         repositoryCategory.save(category);
        logger.info("Category created: {}", category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Category>> createCategory(List<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repositoryCategory.saveAll(categories);
        logger.info("Category's created: {}", categories);
        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Category> getCategoryById(Long id) {
        Optional<Category> category = repositoryCategory.findById(id);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @Override
    public ResponseEntity<List<Category>> allCategories() {
        List<Category> categories = repositoryCategory.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> updateCategory(Category category, Long id) {
        Optional<Category> categoryOptional = repositoryCategory.findById(id);
        if (categoryOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        categoryOptional.get().setName(category.getName());
        categoryOptional.get().setDescription(category.getDescription());
        categoryOptional.get().setProductSet(category.getProductSet());
        repositoryCategory.save(categoryOptional.get());

        logger.info("Category updated: {}", category);
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> deleteCategory(Long id) {

        Optional<Category> categoryOptional = repositoryCategory.findById(id);
        if (categoryOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Category deleted: {}", categoryOptional.get());
        repositoryCategory.deleteById(id);
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }
}
