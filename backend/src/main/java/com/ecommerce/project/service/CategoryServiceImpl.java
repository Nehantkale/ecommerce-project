package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    //private List<Category> categories = new ArrayList<>();
   // private Long nextID = 1l;
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);

    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()  -> new ResponseStatusException(HttpStatus.NOT_FOUND,"resourse not found"));
        List<Category> categories = categoryRepository.findAll();
        categoryRepository.delete(category);
        return "Category with CategoryId : " +categoryId+" deleted successfully !! ";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category saveCategory = categoryRepository.findById(categoryId)
                .orElseThrow(()  -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resoure not found"));

        category.setCategoryId(categoryId);
        saveCategory=categoryRepository.save(category);
        return saveCategory;
       

    }
}
