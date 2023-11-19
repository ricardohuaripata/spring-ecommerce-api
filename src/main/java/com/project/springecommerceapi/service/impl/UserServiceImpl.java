package com.project.springecommerceapi.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.springecommerceapi.common.AppConstants;
import com.project.springecommerceapi.common.UserPrincipal;
import com.project.springecommerceapi.dto.ResetPasswordDto;
import com.project.springecommerceapi.dto.SignupDto;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.enumeration.Role;
import com.project.springecommerceapi.enumeration.TokenType;
import com.project.springecommerceapi.exceptions.AlreadyVerifiedEmailException;
import com.project.springecommerceapi.exceptions.EmailExistsException;
import com.project.springecommerceapi.exceptions.InvalidTokenException;
import com.project.springecommerceapi.exceptions.SizeColorProductVariantExistsException;
import com.project.springecommerceapi.exceptions.UserNotFoundException;
import com.project.springecommerceapi.repository.UserRepository;
import com.project.springecommerceapi.security.JwtTokenService;
import com.project.springecommerceapi.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailService;
    private final JwtTokenService jwtTokenService;

    @Override
    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User createNewUser(SignupDto signupDto) {
        try {
            getUserByEmail(signupDto.getEmail());
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
                    jwtTokenService.generateToken(userPrincipal, TokenType.EMAIL_VERIFICATION_TOKEN.name()),
                    savedUser.getFirstName());
            emailService.send(savedUser.getEmail(), AppConstants.VERIFY_EMAIL, emailVerifyMail);

            return savedUser;
        }
    }

    @Override
    public User verifyEmail(String token) {
        String targetEmail = jwtTokenService.getSubjectFromToken(token);

        if (jwtTokenService.isTokenValid(targetEmail, token, TokenType.EMAIL_VERIFICATION_TOKEN.name()) == false) {
            throw new InvalidTokenException();
        }
        
        User targetUser = getUserByEmail(targetEmail);

        if (targetUser.getEmailVerified() == true) {
            throw new AlreadyVerifiedEmailException();
        }
        targetUser.setEmailVerified(true);
        targetUser.setDateLastModified(new Date());
        return userRepository.save(targetUser);
    }

    @Override
    public void forgotPassword(String email) {
        User targetUser = getUserByEmail(email);
        UserPrincipal userPrincipal = new UserPrincipal(targetUser);
        String emailVerifyMail = emailService.buildResetPasswordMail(
                jwtTokenService.generateToken(userPrincipal, TokenType.PASSWORD_RESET_TOKEN.name()));
        emailService.send(targetUser.getEmail(), AppConstants.RESET_PASSWORD, emailVerifyMail);
    }

    @Override
    public User resetPassword(String token, ResetPasswordDto resetPasswordDto) {
        String targetUserEmail = jwtTokenService.getSubjectFromToken(token);
        User targetUser = getUserByEmail(targetUserEmail);
        targetUser.setPassword(passwordEncoder.encode(resetPasswordDto.getPassword()));
        targetUser.setDateLastModified(new Date());
        return userRepository.save(targetUser);
    }

}
