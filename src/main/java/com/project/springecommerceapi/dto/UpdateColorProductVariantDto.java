package com.project.springecommerceapi.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

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
public class UpdateColorProductVariantDto {
    @NotNull
    @DecimalMin(value = "0.01", message = "The minimum price allowed is 0.01")
    @DecimalMax(value = "100000.00", message = "The maximum price allowed is 100000.00")
    private BigDecimal basePrice;

    @NotNull
    @DecimalMin(value = "0.01", message = "The minimum price allowed is 0.01")
    @DecimalMax(value = "100000.00", message = "The maximum price allowed is 100000.00")
    private BigDecimal finalPrice;
}
