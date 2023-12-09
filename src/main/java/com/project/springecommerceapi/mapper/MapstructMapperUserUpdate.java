package com.project.springecommerceapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.project.springecommerceapi.dto.UpdateUserDto;
import com.project.springecommerceapi.entity.User;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MapstructMapperUserUpdate {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "emailVerified", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateLastModified", ignore = true)

    void updateUserFromUpdateUserDto(
            UpdateUserDto updateUserDto,
            @MappingTarget User user);
}
