package com.project.springecommerceapi.service;

import java.util.UUID;

import com.project.springecommerceapi.dto.CartItemDto;
import com.project.springecommerceapi.entity.Cart;
import com.project.springecommerceapi.entity.CartItem;

public interface ICartService {
    Cart getCartById(UUID cartId);

    Cart createCart();

    CartItem getCartItemById(UUID cartItemId);

    Cart createCartItem(UUID cartId, CartItemDto cartItemDto);

    Cart deleteCartItem(UUID cartItemId);

    void clearCart(UUID cartId);

    int deleteExpiredCarts();

}
