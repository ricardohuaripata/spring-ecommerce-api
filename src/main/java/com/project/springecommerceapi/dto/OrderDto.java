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
    @Size(max = 20)
    private String cardNumber;

    @NotBlank
    @Size(min = 2, max = 2)
    private String expMonth;

    @NotBlank
    @Size(min = 2, max = 2)
    private String expYear;

    @NotBlank
    @Size(min = 3, max = 4)
    private String cvc;

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
