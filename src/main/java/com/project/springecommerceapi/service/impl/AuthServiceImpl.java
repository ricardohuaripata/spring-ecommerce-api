package com.project.springecommerceapi.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.springecommerceapi.common.AppConstants;
import com.project.springecommerceapi.common.UserPrincipal;
import com.project.springecommerceapi.dto.ForgotPasswordDto;
import com.project.springecommerceapi.dto.LoginDto;
import com.project.springecommerceapi.dto.ResetPasswordDto;
import com.project.springecommerceapi.dto.SignupDto;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.enumeration.Role;
import com.project.springecommerceapi.enumeration.TokenType;
import com.project.springecommerceapi.exceptions.AlreadyVerifiedEmailException;
import com.project.springecommerceapi.exceptions.EmailExistsException;
import com.project.springecommerceapi.exceptions.InvalidTokenException;
import com.project.springecommerceapi.exceptions.UserNotFoundException;
import com.project.springecommerceapi.repository.UserRepository;
import com.project.springecommerceapi.security.JwtTokenService;
import com.project.springecommerceapi.service.IAuthService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final PasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailService;
    private final JwtTokenService jwtTokenService;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public User checkLogin(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));
        return userService.getUserByEmail(loginDto.getEmail());
    }

    @Override
    public User signUp(SignupDto signupDto) {
        try {
            userService.getUserByEmail(signupDto.getEmail());
            // Si no se lanzó la excepción UserNotFoundException, significa que el email ya
            // está cogido
            throw new EmailExistsException();

        } catch (UserNotFoundException e) {
            User newUser = new User();
            newUser.setEmail(signupDto.getEmail());
            newUser.setPassword(passwordEncoder.encode(signupDto.getPassword()));
            newUser.setFirstName(signupDto.getFirstName());
            newUser.setLastName(signupDto.getLastName());
            newUser.setEmailVerified(false);
            Date currentDate = new Date();
            newUser.setDateCreated(currentDate);
            newUser.setDateLastModified(currentDate);
            newUser.setRole(Role.ROLE_USER.name());
            User savedUser = userRepository.save(newUser);
            UserPrincipal userPrincipal = new UserPrincipal(savedUser);

            String emailVerifyMail = emailService.buildEmailVerifyMail(
                    jwtTokenService.generateToken(userPrincipal, TokenType.EMAIL_VERIFICATION_TOKEN),
                    savedUser.getFirstName());
            emailService.send(savedUser.getEmail(), AppConstants.VERIFY_EMAIL, emailVerifyMail);

            return savedUser;
        }
    }

    @Override
    public void requestEmailVerification() {
        User user = userService.getAuthenticatedUser();

        if (user.getEmailVerified() == true) {
            throw new AlreadyVerifiedEmailException();
        }
        UserPrincipal userPrincipal = new UserPrincipal(user);
        String emailVerifyMail = emailService.buildEmailVerifyMail(
                jwtTokenService.generateToken(userPrincipal, TokenType.EMAIL_VERIFICATION_TOKEN),
                user.getFirstName());
        emailService.send(user.getEmail(), AppConstants.VERIFY_EMAIL, emailVerifyMail);
    }

    @Override
    public User verifyEmail(String token) {
        String targetEmail = jwtTokenService.getSubjectFromToken(token);

        if (jwtTokenService.isTokenValid(targetEmail, token, TokenType.EMAIL_VERIFICATION_TOKEN) == false) {
            throw new InvalidTokenException();
        }

        User targetUser = userService.getUserByEmail(targetEmail);
        if (targetUser.getEmailVerified() == true) {
            throw new AlreadyVerifiedEmailException();
        }
        targetUser.setEmailVerified(true);
        targetUser.setDateLastModified(new Date());
        return userRepository.save(targetUser);
    }

    @Override
    public void forgotPassword(ForgotPasswordDto forgotPasswordDto) {
        User targetUser = userService.getUserByEmail(forgotPasswordDto.getEmail());
        UserPrincipal userPrincipal = new UserPrincipal(targetUser);
        String emailVerifyMail = emailService.buildResetPasswordMail(
                jwtTokenService.generateToken(userPrincipal, TokenType.PASSWORD_RESET_TOKEN));
        emailService.send(targetUser.getEmail(), AppConstants.RESET_PASSWORD, emailVerifyMail);
    }

    @Override
    public User resetPassword(String token, ResetPasswordDto resetPasswordDto) {
        String targetEmail = jwtTokenService.getSubjectFromToken(token);

        if (jwtTokenService.isTokenValid(targetEmail, token, TokenType.PASSWORD_RESET_TOKEN) == false) {
            throw new InvalidTokenException();
        }

        User targetUser = userService.getUserByEmail(targetEmail);
        targetUser.setPassword(passwordEncoder.encode(resetPasswordDto.getPassword()));
        if (targetUser.getEmailVerified() == false) {
            targetUser.setEmailVerified(true);
        }
        targetUser.setDateLastModified(new Date());
        return userRepository.save(targetUser);
    }

}
