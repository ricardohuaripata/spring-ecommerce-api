package com.project.springecommerceapi.service;

import java.util.UUID;

import com.project.springecommerceapi.dto.ResetPasswordDto;
import com.project.springecommerceapi.dto.SignupDto;
import com.project.springecommerceapi.entity.User;

public interface IUserService {

    User getUserById(UUID userId);

    User getUserByEmail(String email);

    User createNewUser(SignupDto signupDto);

    User verifyEmail(String token);

    void forgotPassword(String email);

    User resetPassword(String token, ResetPasswordDto resetPasswordDto);
}
