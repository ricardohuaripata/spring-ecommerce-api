package com.project.springecommerceapi.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotNull
    private UUID categoryId;

    @NotBlank
    @Size(max = 64)
    private String title;

    @NotBlank
    @Size(max = 64)
    // Expresión regular para permitir letras minúsculas, números y guiones
    @Pattern(regexp = "^[a-z0-9-]+$", message = "Invalid slug, only lowercase letters, numbers and hyphens are allowed")
    private String slug;

    @NotBlank
    @Size(max = 1024)
    private String description;

}
