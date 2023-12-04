package com.project.springecommerceapi.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springecommerceapi.dto.ProductDto;
import com.project.springecommerceapi.entity.Category;
import com.project.springecommerceapi.entity.Product;
import com.project.springecommerceapi.exceptions.ProductNotFoundException;
import com.project.springecommerceapi.exceptions.SlugExistsException;
import com.project.springecommerceapi.service.IProductService;
import com.project.springecommerceapi.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryServiceImpl categoryService;

    @Override
    public Product getProductById(UUID productId) {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

    }

    @Override
    public Product getProductBySlug(String slug) {
        return productRepository.findBySlug(slug).orElseThrow(ProductNotFoundException::new);

    }

    @Override
    public Product createProduct(ProductDto productDto) {
        try {
            getProductBySlug(productDto.getSlug());
            // Si no se lanzó la excepción ProductNotFoundException, significa que el slug
            // ya existe
            throw new SlugExistsException();

        } catch (ProductNotFoundException e) {

            Category category = categoryService.getCategoryById(productDto.getCategoryId());

            Product newProduct = new Product();

            newProduct.setCategory(category);
            newProduct.setTitle(productDto.getTitle());
            newProduct.setSlug(productDto.getSlug());
            newProduct.setDescription(productDto.getDescription());
            Date currentDate = new Date();
            newProduct.setDateCreated(currentDate);
            newProduct.setDateLastModified(currentDate);

            return productRepository.save(newProduct);
        }
    }

    @Override
    public Page<Product> getAllProductsPaginate(Integer page, Integer size) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreated")));

    }

    @Override
    public Page<Product> getProductsByCategoryPaginate(UUID categoryId, Integer page, Integer size) {
        Category category = categoryService.getCategoryById(categoryId);
        return productRepository.findProductsByCategory(category, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreated")));
    }

}
