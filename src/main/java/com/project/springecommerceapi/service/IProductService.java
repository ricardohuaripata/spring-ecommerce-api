package com.project.springecommerceapi.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.project.springecommerceapi.dto.ProductDto;
import com.project.springecommerceapi.entity.Category;
import com.project.springecommerceapi.entity.Product;

public interface IProductService {

    Product getProductById(UUID productId);

    Product getProductBySlug(String slug);

    Product createProduct(ProductDto productDto);

    Page<Product> getAllProductsPaginate(Integer page, Integer size);

    Page<Product> getProductsByCategoryPaginate(Category category, Integer page, Integer size);

}
