package com.project.springecommerceapi.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.springecommerceapi.dto.SignupDto;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.enumeration.Role;
import com.project.springecommerceapi.exceptions.EmailExistsException;
import com.project.springecommerceapi.exceptions.UserNotFoundException;
import com.project.springecommerceapi.repository.UserRepository;
import com.project.springecommerceapi.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

            return userRepository.save(newUser);
        }
    }

}
