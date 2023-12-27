package com.project.springecommerceapi.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springecommerceapi.common.AppConstants;
import com.project.springecommerceapi.dto.ShippingAddressDto;
import com.project.springecommerceapi.dto.UpdatePasswordDto;
import com.project.springecommerceapi.dto.UpdateShippingAddressDto;
import com.project.springecommerceapi.dto.UpdateUserDto;
import com.project.springecommerceapi.entity.ShippingAddress;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.response.SuccessResponse;
import com.project.springecommerceapi.service.impl.AuthServiceImpl;
import com.project.springecommerceapi.service.impl.ShippingAddressServiceImpl;
import com.project.springecommerceapi.service.impl.UserServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @GetMapping("/account/details")
    @ApiOperation(value = "Show Authenticated User Profile", notes = "Retrieve user's profile information")
    public ResponseEntity<?> showUserProfile() {
        User user = userService.getAuthenticatedUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/account/details")
    @ApiOperation(value = "Update User Details", notes = "Update authenticated user details")
    public ResponseEntity<?> updateAuthenticatedUserDetails(@RequestBody @Valid UpdateUserDto updateUserDto) {

        User updatedUser = userService.updateAuthenticatedUserDetails(updateUserDto);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PatchMapping("/account/password")
    @ApiOperation(value = "Update User Password", notes = "Update authenticated user password")
    public ResponseEntity<?> updateUserPassword(@RequestBody @Valid UpdatePasswordDto updatePasswordDto) {
        User updatedUser = userService.updatePassword(updatePasswordDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/account/shipping-address")
    @ApiOperation(value = "Get User's Shipping Addresses", notes = "Retrieve list of user's shipping addresses")
    public ResponseEntity<?> getShippingAddressList() {
        List<ShippingAddress> shippingAddressList = shippingAddressService.getAuthenticatedUserShippingAddressList();
        return new ResponseEntity<>(shippingAddressList, HttpStatus.OK);
    }

    @PostMapping("/account/shipping-address")
    @ApiOperation(value = "Create Shipping Address", notes = "Create a new shipping address for the user")
    public ResponseEntity<?> createNewShippingAddress(
            @ApiParam(value = "Details of new shipping address", required = true) @RequestBody @Valid ShippingAddressDto shippingAddressDto) {
        ShippingAddress createdShippingAddress = shippingAddressService.createNewShippingAddress(shippingAddressDto);
        return new ResponseEntity<>(createdShippingAddress, HttpStatus.CREATED);
    }

    @DeleteMapping("/account/shipping-address/{shippingAddressId}")
    @ApiOperation(value = "Delete Shipping Address", notes = "Delete a shipping address of the authenticated user")
    public ResponseEntity<?> deleteShippingAddress(@PathVariable("shippingAddressId") UUID shippingAddressId) {

        shippingAddressService.deleteShippingAddress(shippingAddressId);
        SuccessResponse successResponse = SuccessResponse.builder()
                .type("Success")
                .message("Shipping address deleted successfully.")
                .build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PatchMapping("/account/shipping-address/{shippingAddressId}")
    @ApiOperation(value = "Update Shipping Address", notes = "Update a shipping address of the authenticated user")
    public ResponseEntity<?> updateShippingAddress(@PathVariable("shippingAddressId") UUID shippingAddressId,
            @RequestBody @Valid UpdateShippingAddressDto updateShippingAddressDto) {

        ShippingAddress updatedShippingAddress = shippingAddressService.updateShippingAddressDetails(shippingAddressId,
                updateShippingAddressDto);

        return new ResponseEntity<>(updatedShippingAddress, HttpStatus.OK);
    }

    @PostMapping("/account/email-verification")
    @ApiOperation(value = "Request Email Verification", notes = "Request email verification for the user")
    public ResponseEntity<?> requestEmailVerification() {
        authService.requestEmailVerification();
        SuccessResponse successResponse = SuccessResponse.builder()
                .type("Success")
                .message(AppConstants.CHECK_EMAIL)
                .build();
        return new ResponseEntity<>(successResponse, HttpStatus.ACCEPTED);
    }

}
