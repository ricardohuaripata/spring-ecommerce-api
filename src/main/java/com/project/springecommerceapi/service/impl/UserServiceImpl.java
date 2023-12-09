package com.project.springecommerceapi.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springecommerceapi.dto.UpdateUserDto;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.exceptions.UserNotFoundException;
import com.project.springecommerceapi.mapper.MapstructMapperUserUpdate;
import com.project.springecommerceapi.repository.UserRepository;
import com.project.springecommerceapi.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final MapstructMapperUserUpdate mapstructMapperUserUpdate;

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
    public User updateAuthenticatedUserDetails(UpdateUserDto updateUserDto) {
        User authUser = getAuthenticatedUser();
        mapstructMapperUserUpdate.updateUserFromUpdateUserDto(
                updateUserDto, authUser);
        authUser.setDateLastModified(new Date());

        return userRepository.save(authUser);
    }

}
