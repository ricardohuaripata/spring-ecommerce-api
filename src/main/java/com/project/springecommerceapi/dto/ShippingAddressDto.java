package com.project.springecommerceapi.dto;

import javax.validation.constraints.NotEmpty;
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
public class ShippingAddressDto {

    @NotEmpty
    @Size(max = 64)
    private String firstName;

    @NotEmpty
    @Size(max = 64)
    private String lastName;

    @NotEmpty
    @Size(max = 64)
    private String country;

    @NotEmpty
    @Size(max = 64)
    private String city;

    @NotEmpty
    @Size(max = 10)
    private String postalCode;

    @NotEmpty
    @Size(max = 1024)
    private String address;

    @NotEmpty
    @Size(max = 20)
    private String contactPhone;
}
