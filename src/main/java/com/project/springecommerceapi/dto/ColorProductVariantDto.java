package com.project.springecommerceapi.dto;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class ColorProductVariantDto {

    @NotNull
    private UUID productId;

    @NotNull
    private UUID colorId;

    @NotNull
    @DecimalMin(value = "0.01", message = "The minimum price allowed is 0.01")
    @DecimalMax(value = "100000.00", message = "The maximum price allowed is 100000.00")
    private BigDecimal basePrice;

    @NotNull
    @DecimalMin(value = "0.01", message = "The minimum price allowed is 0.01")
    @DecimalMax(value = "100000.00", message = "The maximum price allowed is 100000.00")
    private BigDecimal finalPrice;

    @NotBlank
    @Size(max = 1024)
    private String frontImageUrl;

    @NotBlank
    @Size(max = 1024)
    private String backImageUrl;

}
