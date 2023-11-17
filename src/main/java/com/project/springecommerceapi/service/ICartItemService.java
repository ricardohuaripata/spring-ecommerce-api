package com.project.springecommerceapi.service;

import java.util.UUID;

import com.project.springecommerceapi.dto.CartItemDto;
import com.project.springecommerceapi.entity.Cart;
import com.project.springecommerceapi.entity.CartItem;

public interface ICartItemService {
    CartItem getCartItemById(UUID cartItemId);

    CartItem createCartItem(Cart cart, CartItemDto cartItemDto);

    void deleteCartItem(CartItem cartItem);

}
