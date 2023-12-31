package com.project.springecommerceapi.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springecommerceapi.dto.UpdatePasswordDto;
import com.project.springecommerceapi.dto.UpdateUserDto;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.exceptions.InvalidOperationException;
import com.project.springecommerceapi.exceptions.InvalidOldPasswordException;
import com.project.springecommerceapi.exceptions.UserNotFoundException;
import com.project.springecommerceapi.mapper.MapstructMapperUserUpdate;
import com.project.springecommerceapi.repository.UserRepository;
import com.project.springecommerceapi.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final MapstructMapperUserUpdate mapstructMapperUserUpdate;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public final User getAuthenticatedUser() {
        String authUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return getUserByEmail(authUserEmail);
    }

    @Override
    @Transactional
    public User updateAuthenticatedUserDetails(UpdateUserDto updateUserDto) {
        User authUser = getAuthenticatedUser();
        mapstructMapperUserUpdate.updateUserFromUpdateUserDto(
                updateUserDto, authUser);
        authUser.setDateLastModified(new Date());

        return userRepository.save(authUser);
    }

    @Override
    @Transactional
    public User updatePassword(UpdatePasswordDto updatePasswordDto) {
        User authUser = getAuthenticatedUser();
        if (passwordEncoder.matches(updatePasswordDto.getOldPassword(), authUser.getPassword())) {
            authUser.setPassword(passwordEncoder.encode(updatePasswordDto.getPassword()));
            authUser.setDateLastModified(new Date());
            return userRepository.save(authUser);
        } else {
            throw new InvalidOldPasswordException();
        }
    }

}
