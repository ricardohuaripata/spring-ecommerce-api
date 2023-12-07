package com.project.springecommerceapi.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springecommerceapi.common.AppConstants;
import com.project.springecommerceapi.common.UserPrincipal;
import com.project.springecommerceapi.dto.ForgotPasswordDto;
import com.project.springecommerceapi.dto.LoginDto;
import com.project.springecommerceapi.dto.ResetPasswordDto;
import com.project.springecommerceapi.dto.SignupDto;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.enumeration.TokenType;
import com.project.springecommerceapi.response.SuccessResponse;
import com.project.springecommerceapi.security.JwtTokenService;
import com.project.springecommerceapi.service.impl.AuthServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupDto signupDto) {
        User savedUser = authService.signUp(signupDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto) {

        User loginUser = authService.checkLogin(loginDto);
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);

        HttpHeaders newHttpHeaders = new HttpHeaders();
        newHttpHeaders.add(AppConstants.TOKEN_HEADER,
                jwtTokenService.generateToken(userPrincipal, TokenType.AUTHENTICATION_TOKEN));

        return new ResponseEntity<>(loginUser, newHttpHeaders, HttpStatus.OK);
    }

    @PostMapping("/verify-email/{token}")
    @ApiOperation(value = "Verify Email", notes = "Verify user's email using a email-verification token")
    public ResponseEntity<?> verifyEmail(
            @ApiParam(value = "Token for email verification", required = true) @PathVariable("token") String token) {
        User user = authService.verifyEmail(token);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    @ApiOperation(value = "Forgot Password", notes = "Request to reset the user's forgotten password")
    public ResponseEntity<?> forgotPassword(
            @ApiParam(value = "Details for resetting password", required = true) @RequestBody @Valid ForgotPasswordDto forgotPasswordDto) {
        authService.forgotPassword(forgotPasswordDto);
        SuccessResponse successResponse = SuccessResponse.builder()
                .type("Success")
                .message(AppConstants.CHECK_EMAIL)
                .build();
        return new ResponseEntity<>(successResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/reset-password/{token}")
    @ApiOperation(value = "Reset Password", notes = "Reset user's password using a password-reset token")
    public ResponseEntity<?> resetPassword(
            @ApiParam(value = "Details for resetting password", required = true) @RequestBody @Valid ResetPasswordDto resetPasswordDto,
            @ApiParam(value = "Token for resetting password", required = true) @PathVariable("token") String token) {
        User user = authService.resetPassword(token, resetPasswordDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
