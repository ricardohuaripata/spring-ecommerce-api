package com.project.springecommerceapi.service;

import java.util.UUID;

import com.project.springecommerceapi.dto.CartItemDto;
import com.project.springecommerceapi.entity.Cart;
import com.project.springecommerceapi.entity.CartItem;
import com.project.springecommerceapi.response.CartResponse;

public interface ICartService {
    Cart getCartById(UUID cartId);

    CartResponse getCartResponseById(UUID cartId);

    CartResponse createCart();

    CartItem getCartItemById(UUID cartItemId);

    CartResponse addToCart(UUID cartId, CartItemDto cartItemDto);

    CartResponse deleteCartItem(UUID cartItemId);

    CartResponse clearCart(UUID cartId);

    int deleteExpiredCarts();

}
