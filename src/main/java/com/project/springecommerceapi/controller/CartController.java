package com.project.springecommerceapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springecommerceapi.common.AppConstants;
import com.project.springecommerceapi.dto.CartItemDto;
import com.project.springecommerceapi.entity.Cart;
import com.project.springecommerceapi.entity.CartItem;
import com.project.springecommerceapi.repository.CartRepository;
import com.project.springecommerceapi.service.impl.CartItemServiceImpl;
import com.project.springecommerceapi.service.impl.CartServiceImpl;

import java.util.Date;
import java.util.UUID;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartServiceImpl cartService;
    private final CartItemServiceImpl cartItemService;
    private final CartRepository cartRepository;

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable("cartId") UUID cartId) {
        Cart cartToFind = cartService.getCartById(cartId);
        return new ResponseEntity<>(cartToFind, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createCart() {
        Cart createdCart = cartService.createCart();
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @PostMapping("/{cartId}")
    public ResponseEntity<?> addToCart(@PathVariable("cartId") UUID cartId,
            @RequestBody @Valid CartItemDto cartItemDto) {

        Cart cart = cartService.getCartById(cartId);
        CartItem createdCartItem = cartItemService.createCartItem(cart, cartItemDto);

        cart.setDateLastModified(new Date());
        cart.setDateExpiration(new Date(System.currentTimeMillis() + AppConstants.CART_EXPIRATION_4Wk));

        cart = cartRepository.save(cart);

        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable("cartItemId") UUID cartItemId) {

        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        cartItemService.deleteCartItem(cartItem);

        Cart cart = cartService.getCartById(cartItem.getCart().getId());

        cart.setDateLastModified(new Date());
        cart.setDateExpiration(new Date(System.currentTimeMillis() + AppConstants.CART_EXPIRATION_4Wk));
        cart = cartRepository.save(cart);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

}
