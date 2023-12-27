package com.project.springecommerceapi.dto;

import com.project.springecommerceapi.annotation.PasswordRepeatEqual;
import com.project.springecommerceapi.annotation.ValidPassword;

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
public class UpdatePasswordDto {
    private String oldPassword;

    @ValidPassword
    private String password;
    private String passwordRepeat;

}
