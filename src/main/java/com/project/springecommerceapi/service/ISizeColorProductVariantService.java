package com.project.springecommerceapi.service;

import java.util.List;
import java.util.UUID;

import com.project.springecommerceapi.dto.SizeColorProductVariantDto;
import com.project.springecommerceapi.entity.SizeColorProductVariant;

public interface ISizeColorProductVariantService {

    SizeColorProductVariant getSizeColorProductVariantById(UUID sizeColorProductVariantId);

    List<SizeColorProductVariant> getSizeColorProductVariantsByColorProductVariant(
            UUID colorProductVariantId);

    SizeColorProductVariant createSizeColorProductVariant(SizeColorProductVariantDto sizeColorProductVariantDto);

}
