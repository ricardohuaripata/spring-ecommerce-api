package com.project.springecommerceapi.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.springecommerceapi.entity.ColorProductVariant;
import com.project.springecommerceapi.entity.ProductImage;
import com.project.springecommerceapi.exceptions.InvalidImageFileException;
import com.project.springecommerceapi.exceptions.ProductImageNotFoundException;
import com.project.springecommerceapi.repository.ProductImageRepository;
import com.project.springecommerceapi.service.IProductImageService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductImageServiceImpl implements IProductImageService {

    private final ColorProductVariantServiceImpl colorProductVariantService;
    private final StorageServiceImpl storageService;
    private final ProductImageRepository productImageRepository;

    private final List<String> allowedImageTypes = Arrays.asList("image/jpeg", "image/png", "image/jpg");

    @Override
    public ProductImage getProductImageById(Long productImageId) {
        return productImageRepository.findById(productImageId)
                .orElseThrow(ProductImageNotFoundException::new);
    }

    @Override
    public ProductImage uploadProductImage(UUID colorProductVariantId, MultipartFile file) {
        // Verificar el tipo de archivo
        if (!allowedImageTypes.contains(file.getContentType())) {
            throw new InvalidImageFileException();
        }

        ColorProductVariant colorProductVariant = colorProductVariantService
                .getColorProductVariantById(colorProductVariantId);
        String uploadedFile = storageService.uploadFile(file);

        ProductImage productImage = new ProductImage();

        productImage.setColorProductVariant(colorProductVariant);
        productImage.setFileName(uploadedFile);
        productImage.setFileUrl(storageService.getFileUrl(uploadedFile));
        productImage.setDateCreated(new Date());

        return productImageRepository.save(productImage);
    }

    @Override
    public String deleteProductImage(Long productImageId) {
        ProductImage productImage = getProductImageById(productImageId);
        productImageRepository.delete(productImage);
        return storageService.deleteFile(productImage.getFileName());

    }

}
