package com.project.springecommerceapi.service;

import java.util.UUID;

import com.project.springecommerceapi.entity.User;

public interface IUserService {

    User getUserById(UUID userId);

    User getUserByEmail(String email);
}
