package com.project.springecommerceapi.service;

import java.util.List;
import java.util.UUID;

import com.project.springecommerceapi.dto.CategoryDto;
import com.project.springecommerceapi.entity.Category;

public interface ICategoryService {

    Category getCategoryById(UUID categoryId);

    Category getCategoryBySlug(String slug);

    Category createCategory(CategoryDto categoryDto);

    List<Category> getAllCategories();

}