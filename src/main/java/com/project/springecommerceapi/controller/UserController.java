package com.project.springecommerceapi.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springecommerceapi.dto.ShippingAddressDto;
import com.project.springecommerceapi.entity.ShippingAddress;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.service.impl.ShippingAddressServiceImpl;
import com.project.springecommerceapi.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserServiceImpl userService;
    private final ShippingAddressServiceImpl shippingAddressService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") UUID userId) {
        User userToFind = userService.getUserById(userId);
        return new ResponseEntity<>(userToFind, HttpStatus.OK);
    }

    @GetMapping("/account/info")
    public ResponseEntity<?> showUserProfile(Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getPrincipal().toString());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/account/shipping-address")
    public ResponseEntity<?> getShippingAddressList(Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getPrincipal().toString());
        List<ShippingAddress> shippingAddressList = shippingAddressService.getShippingAddressListByUser(user);

        return new ResponseEntity<>(shippingAddressList, HttpStatus.OK);
    }

    @PostMapping("/account/shipping-address")
    public ResponseEntity<?> createNewShippingAddress(Authentication authentication,
            @RequestBody @Valid ShippingAddressDto shippingAddressDto) {
        User user = userService.getUserByEmail(authentication.getPrincipal().toString());
        ShippingAddress createdShippingAddress = shippingAddressService.createNewShippingAddress(user,
                shippingAddressDto);

        return new ResponseEntity<>(createdShippingAddress, HttpStatus.CREATED);
    }

}
