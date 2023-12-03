package com.project.springecommerceapi.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.springecommerceapi.dto.ColorProductVariantDto;
import com.project.springecommerceapi.entity.Category;
import com.project.springecommerceapi.entity.Color;
import com.project.springecommerceapi.entity.ColorProductVariant;
import com.project.springecommerceapi.entity.Product;
import com.project.springecommerceapi.entity.ProductImage;
import com.project.springecommerceapi.exceptions.ColorProductVariantExistsException;
import com.project.springecommerceapi.exceptions.ColorProductVariantNotFoundException;
import com.project.springecommerceapi.exceptions.InvalidImageFileException;
import com.project.springecommerceapi.service.IColorProductVariantService;
import com.project.springecommerceapi.repository.ColorProductVariantRepository;
import com.project.springecommerceapi.repository.ProductImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ColorProductVariantServiceImpl implements IColorProductVariantService {

    private final ColorProductVariantRepository colorProductVariantRepository;
    private final ProductServiceImpl productService;
    private final ColorServiceImpl colorService;
    private final StorageServiceImpl storageService;
    private final ProductImageRepository productImageRepository;

    private final List<String> allowedImageTypes = Arrays.asList("image/jpeg", "image/png", "image/jpg");

    @Override
    public ColorProductVariant getColorProductVariantById(UUID colorProductVariantId) {
        return colorProductVariantRepository.findById(colorProductVariantId)
                .orElseThrow(ColorProductVariantNotFoundException::new);
    }

    @Override
    public List<ColorProductVariant> getColorProductVariantsByProduct(Product product) {
        return colorProductVariantRepository.findColorProductVariantsByProduct(product);
    }

    @Override
    public ColorProductVariant createColorProductVariant(ColorProductVariantDto colorProductVariantDto) {

        Product product = productService.getProductById(colorProductVariantDto.getProductId());
        Color color = colorService.getColorById(colorProductVariantDto.getColorId());

        // Verificar si ya existe una variación para el producto y color dados
        if (colorProductVariantRepository.existsByProductAndColor(product, color)) {
            // Manejar el caso en el que ya existe una variación
            throw new ColorProductVariantExistsException();
        }

        ColorProductVariant newColorProductVariant = new ColorProductVariant();

        newColorProductVariant.setProduct(product);
        newColorProductVariant.setColor(color);
        newColorProductVariant.setBasePrice(colorProductVariantDto.getBasePrice());
        newColorProductVariant.setFinalPrice(colorProductVariantDto.getFinalPrice());
        Date currentDate = new Date();
        newColorProductVariant.setDateCreated(currentDate);
        newColorProductVariant.setDateLastModified(currentDate);

        return colorProductVariantRepository.save(newColorProductVariant);
    }

    @Override
    public Page<ColorProductVariant> getColorProductVariantsByProductCategoryPaginate(Category category, Integer page,
            Integer size) {
        return colorProductVariantRepository.findColorProductVariantsByProduct_Category(category,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreated")));
    }

    @Override
    public ProductImage uploadProductImage(UUID colorProductVariantId, MultipartFile file, int orderPosition) {

        // Verificar el tipo de archivo
        if (!allowedImageTypes.contains(file.getContentType())) {
            throw new InvalidImageFileException();
        }

        ColorProductVariant colorProductVariant = getColorProductVariantById(colorProductVariantId);
        String uploadedFile = storageService.uploadFile(file);

        ProductImage productImage = new ProductImage();

        productImage.setColorProductVariant(colorProductVariant);
        productImage.setFileName(uploadedFile);
        productImage.setFileUrl(storageService.getFileUrl(uploadedFile));
        productImage.setOrderPosition(orderPosition);

        return productImageRepository.save(productImage);

    }

}
