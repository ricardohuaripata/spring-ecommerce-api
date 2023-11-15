package com.project.springecommerceapi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springecommerceapi.dto.CategoryDto;
import com.project.springecommerceapi.entity.Category;
import com.project.springecommerceapi.exceptions.CategoryNotFoundException;
import com.project.springecommerceapi.exceptions.SlugExistsException;
import com.project.springecommerceapi.service.ICategoryService;
import com.project.springecommerceapi.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);

    }

    @Override
    public Category getCategoryBySlug(String slug) {
        return categoryRepository.findBySlug(slug).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Category createCategory(CategoryDto categoryDto) {

        try {
            getCategoryBySlug(categoryDto.getSlug());

            // Si no se lanzó la excepción CategoryNotFoundException, significa que el slug ya existe
            throw new SlugExistsException();

        } catch (CategoryNotFoundException e) {
            Category newCategory = new Category();

            newCategory.setTitle(categoryDto.getTitle());
            newCategory.setSlug(categoryDto.getSlug());
            newCategory.setFeaturedImageUrl(categoryDto.getFeaturedImageUrl());
            Date currentDate = new Date();
            newCategory.setDateCreated(currentDate);
            newCategory.setDateLastModified(currentDate);

            return categoryRepository.save(newCategory);
        }

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
