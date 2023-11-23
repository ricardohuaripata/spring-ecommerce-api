package com.project.springecommerceapi.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springecommerceapi.common.AppConstants;
import com.project.springecommerceapi.dto.ShippingAddressDto;
import com.project.springecommerceapi.entity.ShippingAddress;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.response.SuccessResponse;
import com.project.springecommerceapi.service.impl.AuthServiceImpl;
import com.project.springecommerceapi.service.impl.ShippingAddressServiceImpl;
import com.project.springecommerceapi.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final ShippingAddressServiceImpl shippingAddressService;
    private final AuthServiceImpl authService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") UUID userId) {
        User userToFind = userService.getUserById(userId);
        return new ResponseEntity<>(userToFind, HttpStatus.OK);
    }

    @GetMapping("/account/info")
    public ResponseEntity<?> showUserProfile() {
        User user = userService.getAuthenticatedUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/account/shipping-address")
    public ResponseEntity<?> getShippingAddressList() {

        List<ShippingAddress> shippingAddressList = shippingAddressService.getShippingAddressListByUser();

        return new ResponseEntity<>(shippingAddressList, HttpStatus.OK);
    }

    @PostMapping("/account/shipping-address")
    public ResponseEntity<?> createNewShippingAddress(@RequestBody @Valid ShippingAddressDto shippingAddressDto) {
        ShippingAddress createdShippingAddress = shippingAddressService.createNewShippingAddress(shippingAddressDto);
        return new ResponseEntity<>(createdShippingAddress, HttpStatus.CREATED);
    }

    @PostMapping("/account/email-verification")
    public ResponseEntity<?> requestEmailVerification() {
        authService.requestEmailVerification();
        SuccessResponse successResponse = SuccessResponse.builder()
                .type("Success")
                .message(AppConstants.CHECK_EMAIL)
                .build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

}
