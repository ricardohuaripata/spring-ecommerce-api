package com.project.springecommerceapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.project.springecommerceapi.dto.UpdateShippingAddressDto;
import com.project.springecommerceapi.entity.ShippingAddress;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MapstructMapperShippingAddressUpdate {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateLastModified", ignore = true)

    void updateShippingAddressFromUpdateShippingAddressDto(
            UpdateShippingAddressDto updateShippingAddressDto,
            @MappingTarget ShippingAddress shippingAddress);
}
