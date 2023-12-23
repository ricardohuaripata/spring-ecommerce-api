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
public class UpdateUserDto {
    @Pattern(regexp = "^(\\s*\\S\\s*)*$", message = "Firstname cant't be blank")
    @Size(min = 2, max = 64)
    private String firstName;

    @Pattern(regexp = "^(\\s*\\S\\s*)*$", message = "Lastname cant't be blank")
    @Size(min = 2, max = 64)
    private String lastName;
}
