package com.project.springecommerceapi.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.project.springecommerceapi.dto.ColorProductVariantDto;
import com.project.springecommerceapi.dto.UpdateColorProductVariantDto;
import com.project.springecommerceapi.entity.ColorProductVariant;

public interface IColorProductVariantService {

        ColorProductVariant getColorProductVariantById(UUID colorProductVariantId);

        List<ColorProductVariant> getColorProductVariantsByProductId(UUID productId);

        List<ColorProductVariant> getColorProductVariantsByProductSlugname(String productSlugname);

        Page<ColorProductVariant> getColorProductVariantsByCategorySlugnamePaginate(String categorySlugname,
                        Integer page,
                        Integer size);

        Page<ColorProductVariant> getColorProductVariantsByCategoryIdPaginate(UUID categoryId, Integer page,
                        Integer size);

        ColorProductVariant createColorProductVariant(ColorProductVariantDto colorProductVariantDto,
                        MultipartFile imageFile);

        ColorProductVariant updateColorProductVariantDetails(UUID colorProductVariantId,
                        UpdateColorProductVariantDto updateColorProductVariantDto);

        ColorProductVariant updateColorProductVariantMainImage(UUID colorProductVariantId, MultipartFile imageFile);

}
