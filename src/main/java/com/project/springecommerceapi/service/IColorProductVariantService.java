package com.project.springecommerceapi.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.project.springecommerceapi.dto.ColorProductVariantDto;
import com.project.springecommerceapi.entity.ColorProductVariant;

public interface IColorProductVariantService {
    
    ColorProductVariant getColorProductVariantById(UUID colorProductVariantId);

    List<ColorProductVariant> getColorProductVariantsByProduct(UUID productId);

    Page<ColorProductVariant> getColorProductVariantsByProductCategoryPaginate(UUID categoryId, Integer page,
            Integer size);

    ColorProductVariant createColorProductVariant(ColorProductVariantDto colorProductVariantDto);
}
