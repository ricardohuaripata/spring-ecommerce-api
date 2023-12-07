package com.project.springecommerceapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.project.springecommerceapi.dto.UpdateColorProductVariantDto;
import com.project.springecommerceapi.entity.ColorProductVariant;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MapstructMapperColorProductVariantUpdate {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "color", ignore = true)
    @Mapping(target = "mainImageUrl", ignore = true)
    @Mapping(target = "mainImageName", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateLastModified", ignore = true)

    void updateColorProductVariantFromColorProductVariantUpdateDto(
            UpdateColorProductVariantDto updateColorProductVariantDto,
            @MappingTarget ColorProductVariant colorProductVariant);
}
