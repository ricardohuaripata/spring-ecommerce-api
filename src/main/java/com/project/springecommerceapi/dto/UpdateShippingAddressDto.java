package com.project.springecommerceapi.dto;

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
public class UpdateShippingAddressDto {
    @Pattern(regexp = "^(\\s*\\S\\s*)*$", message = "Firstname cant't be blank")
    @Size(min = 2, max = 64)
    private String firstName;

    @Pattern(regexp = "^(\\s*\\S\\s*)*$", message = "Lastname cant't be blank")
    @Size(min = 2, max = 64)
    private String lastName;

    @Pattern(regexp = "^(?!\\s*$)\\s*\\S.*$", message = "Country cant't be blank")
    @Size(max = 64)
    private String country;

    @Pattern(regexp = "^(?!\\s*$)\\s*\\S.*$", message = "City cant't be blank")
    @Size(max = 64)
    private String city;

    @Pattern(regexp = "^(?!\\s*$)\\s*\\S.*$", message = "Postal code cant't be blank")
    @Size(max = 10)
    private String postalCode;

    @Pattern(regexp = "^(?!\\s*$)\\s*\\S.*$", message = "Address cant't be blank")
    @Size(max = 1024)
    private String address;

    @Pattern(regexp = "^(?!\\s*$)\\s*\\S.*$", message = "Contact phone cant't be blank")
    @Size(max = 20)
    private String contactPhone;
}
