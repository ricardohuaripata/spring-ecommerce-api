package com.project.springecommerceapi.service;

import com.project.springecommerceapi.dto.ForgotPasswordDto;
import com.project.springecommerceapi.dto.LoginDto;
import com.project.springecommerceapi.dto.ResetPasswordDto;
import com.project.springecommerceapi.dto.SignupDto;
import com.project.springecommerceapi.entity.User;

public interface IAuthService {
    User checkLogin(LoginDto loginDto);

    User signUp(SignupDto signupDto);

    void requestEmailVerification();

    User verifyEmail(String token);

    void forgotPassword(ForgotPasswordDto forgotPasswordDto);

    User resetPassword(String token, ResetPasswordDto resetPasswordDto);
}
