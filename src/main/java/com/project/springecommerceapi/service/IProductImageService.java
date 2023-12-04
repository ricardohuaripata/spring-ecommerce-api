package com.project.springecommerceapi.service;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.project.springecommerceapi.entity.ProductImage;

public interface IProductImageService {
    ProductImage getProductImageById(Long productImageId);

    ProductImage uploadProductImage(UUID colorProductVariantId, MultipartFile file);

    String deleteProductImage(Long productImageId);

}
