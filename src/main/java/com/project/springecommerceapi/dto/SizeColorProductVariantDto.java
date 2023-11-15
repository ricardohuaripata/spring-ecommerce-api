package com.project.springecommerceapi.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
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
public class SizeColorProductVariantDto {

    @NotNull
    private UUID colorProductVariantId;

    @NotBlank
    @Size(max = 3)
    @Pattern(regexp = "S|M|L|XL|XXL", message = "Size must be one of: S, M, L, XL, XXL")
    private String size;

    @NotNull
    @PositiveOrZero(message = "Stock cannot be negative")
    private Integer stock;

}
