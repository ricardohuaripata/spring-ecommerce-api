package com.project.springecommerceapi.dto;

import javax.validation.constraints.NotBlank;

import com.project.springecommerceapi.annotation.ValidEmail;

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
public class ForgotPasswordDto {
    @NotBlank
    @ValidEmail
    private String email;
}
