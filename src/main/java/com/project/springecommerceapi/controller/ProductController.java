package com.project.springecommerceapi.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.springecommerceapi.dto.ProductDto;
import com.project.springecommerceapi.entity.Category;
import com.project.springecommerceapi.entity.Product;
import com.project.springecommerceapi.service.impl.CategoryServiceImpl;
import com.project.springecommerceapi.service.impl.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductServiceImpl productService;
    private final CategoryServiceImpl categoryService;

    @GetMapping()
    public ResponseEntity<?> getAllProducts(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        page = page != null && page >= 0 ? page : 0;
        size = size != null && size > 0 ? size : 5;
        Page<Product> productListPage = productService.getAllProductsPaginate(page, size);
        return new ResponseEntity<>(productListPage, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable("productId") UUID productId) {
        Product productToFind = productService.getProductById(productId);
        return new ResponseEntity<>(productToFind, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductDto productDto) {
        Product createdProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable("categoryId") UUID categoryId,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        page = page != null && page >= 0 ? page : 0;
        size = size != null && size > 0 ? size : 5;
        Category category = categoryService.getCategoryById(categoryId);
        Page<Product> productListPage = productService.getProductsByCategoryPaginate(category, page, size);
        return new ResponseEntity<>(productListPage, HttpStatus.OK);
    }

}
