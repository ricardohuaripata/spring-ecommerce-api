package com.project.springecommerceapi.dto;

import java.math.BigDecimal;
import java.util.UUID;

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
public class OrderDto {

    @NotNull
    private UUID cartId;

    @NotBlank
    private String cardToken;

    @NotBlank
    @Size(max = 64)
    private String firstName;

    @NotBlank
    @Size(max = 64)
    private String lastName;

    @NotBlank
    @Size(max = 64)
    private String country;

    @NotBlank
    @Size(max = 64)
    private String city;

    @NotBlank
    @Size(max = 10)
    private String postalCode;

    @NotBlank
    @Size(max = 1024)
    private String address;

    @NotBlank
    @Size(max = 20)
    private String contactPhone;
}
