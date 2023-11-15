package com.project.springecommerceapi.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.project.springecommerceapi.dto.ColorProductVariantDto;
import com.project.springecommerceapi.entity.Category;
import com.project.springecommerceapi.entity.ColorProductVariant;
import com.project.springecommerceapi.entity.Product;

public interface IColorProductVariantService {
    
    ColorProductVariant getColorProductVariantById(UUID colorProductVariantId);

    List<ColorProductVariant> getColorProductVariantsByProduct(Product product);

    Page<ColorProductVariant> getColorProductVariantsByProductCategoryPaginate(Category category, Integer page,
            Integer size);

    ColorProductVariant createColorProductVariant(ColorProductVariantDto colorProductVariantDto);
}
