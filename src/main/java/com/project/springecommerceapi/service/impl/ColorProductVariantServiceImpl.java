package com.project.springecommerceapi.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.springecommerceapi.dto.ColorProductVariantDto;
import com.project.springecommerceapi.dto.UpdateColorProductVariantDto;
import com.project.springecommerceapi.entity.Category;
import com.project.springecommerceapi.entity.Color;
import com.project.springecommerceapi.entity.ColorProductVariant;
import com.project.springecommerceapi.entity.Product;
import com.project.springecommerceapi.exceptions.ColorProductVariantExistsException;
import com.project.springecommerceapi.exceptions.ColorProductVariantNotFoundException;
import com.project.springecommerceapi.exceptions.InvalidImageFileException;
import com.project.springecommerceapi.mapper.MapstructMapperColorProductVariantUpdate;
import com.project.springecommerceapi.service.IColorProductVariantService;
import com.project.springecommerceapi.repository.ColorProductVariantRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ColorProductVariantServiceImpl implements IColorProductVariantService {

    private final ColorProductVariantRepository colorProductVariantRepository;
    private final ProductServiceImpl productService;
    private final ColorServiceImpl colorService;
    private final CategoryServiceImpl categoryService;
    private final StorageServiceImpl storageService;
    private final MapstructMapperColorProductVariantUpdate mapstructMapperColorProductVariantUpdate;
    private final List<String> allowedImageTypes = Arrays.asList("image/jpeg", "image/png", "image/jpg");

    @Override
    public ColorProductVariant getColorProductVariantById(UUID colorProductVariantId) {
        return colorProductVariantRepository.findById(colorProductVariantId)
                .orElseThrow(ColorProductVariantNotFoundException::new);
    }

    @Override
    public List<ColorProductVariant> getColorProductVariantsByProductId(UUID productId) {
        Product product = productService.getProductById(productId);
        return colorProductVariantRepository.findColorProductVariantsByProduct(product);
    }

    @Override
    public ColorProductVariant createColorProductVariant(ColorProductVariantDto colorProductVariantDto,
            MultipartFile imageFile) {
        // Verificar el tipo de archivo
        if (!allowedImageTypes.contains(imageFile.getContentType())) {
            throw new InvalidImageFileException();
        }

        Product product = productService.getProductById(colorProductVariantDto.getProductId());
        Color color = colorService.getColorById(colorProductVariantDto.getColorId());

        // Verificar si ya existe una variación para el producto y color dados
        if (colorProductVariantRepository.existsByProductAndColor(product, color)) {
            // Manejar el caso en el que ya existe una variación
            throw new ColorProductVariantExistsException();
        }

        String uploadedFile = storageService.uploadFile(imageFile);

        ColorProductVariant newColorProductVariant = new ColorProductVariant();

        newColorProductVariant.setProduct(product);
        newColorProductVariant.setColor(color);
        newColorProductVariant.setBasePrice(colorProductVariantDto.getBasePrice());
        newColorProductVariant.setFinalPrice(colorProductVariantDto.getFinalPrice());
        newColorProductVariant.setMainImageUrl(storageService.getFileUrl(uploadedFile));
        newColorProductVariant.setMainImageName(uploadedFile);
        Date currentDate = new Date();
        newColorProductVariant.setDateCreated(currentDate);
        newColorProductVariant.setDateLastModified(currentDate);

        return colorProductVariantRepository.save(newColorProductVariant);
    }

    @Override
    public Page<ColorProductVariant> getColorProductVariantsByCategoryIdPaginate(UUID categoryId, Integer page,
            Integer size) {

        Category category = categoryService.getCategoryById(categoryId);
        return colorProductVariantRepository.findColorProductVariantsByProduct_Category(category,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreated")));
    }

    @Override
    public ColorProductVariant updateColorProductVariantDetails(UUID colorProductVariantId,
            UpdateColorProductVariantDto updateColorProductVariantDto) {

        ColorProductVariant colorProductVariant = getColorProductVariantById(colorProductVariantId);
        mapstructMapperColorProductVariantUpdate.updateColorProductVariantFromColorProductVariantUpdateDto(
                updateColorProductVariantDto, colorProductVariant);
        colorProductVariant.setDateLastModified(new Date());
        return colorProductVariantRepository.save(colorProductVariant);
    }

    @Override
    public ColorProductVariant updateColorProductVariantMainImage(UUID colorProductVariantId, MultipartFile imageFile) {
        // Verificar el tipo de archivo
        if (!allowedImageTypes.contains(imageFile.getContentType())) {
            throw new InvalidImageFileException();
        }

        ColorProductVariant colorProductVariant = getColorProductVariantById(colorProductVariantId);
        try {
            storageService.deleteFile(colorProductVariant.getMainImageName());
        } catch (Exception e) {
        }

        String uploadedFile = storageService.uploadFile(imageFile);
        colorProductVariant.setMainImageUrl(storageService.getFileUrl(uploadedFile));
        colorProductVariant.setMainImageName(uploadedFile);
        colorProductVariant.setDateLastModified(new Date());

        return colorProductVariantRepository.save(colorProductVariant);

    }

    @Override
    public Page<ColorProductVariant> getColorProductVariantsByCategorySlugnamePaginate(String categorySlugname,
            Integer page, Integer size) {
        Category category = categoryService.getCategoryBySlug(categorySlugname);
        return colorProductVariantRepository.findColorProductVariantsByProduct_Category(category,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreated")));
    }

    @Override
    public List<ColorProductVariant> getColorProductVariantsByProductSlugname(String productSlugname) {
        Product product = productService.getProductBySlug(productSlugname);
        return colorProductVariantRepository.findColorProductVariantsByProduct(product);
    }

}
