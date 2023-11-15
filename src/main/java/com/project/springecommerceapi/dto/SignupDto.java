package com.project.springecommerceapi.dto;

import com.project.springecommerceapi.annotation.PasswordRepeatEqual;
import com.project.springecommerceapi.annotation.ValidEmail;
import com.project.springecommerceapi.annotation.ValidPassword;

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
@PasswordRepeatEqual(passwordFieldFirst = "password", passwordFieldSecond = "passwordRepeat")
public class SignupDto {
    @ValidEmail
    private String email;

    @ValidPassword
    private String password;
    private String passwordRepeat;

    @NotEmpty
    @Size(max = 64)
    private String firstName;

    @NotEmpty
    @Size(max = 64)
    private String lastName;
}
