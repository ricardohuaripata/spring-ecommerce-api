package com.project.springecommerceapi.dto;

import com.project.springecommerceapi.annotation.ValidEmail;
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
public class LoginDto {
    @ValidEmail
    private String email;

    @ValidPassword
    private String password;
}
