package com.project.springecommerceapi.service;

import java.util.UUID;

import com.project.springecommerceapi.entity.Cart;

public interface ICartService {
    Cart getCartById(UUID cartId);

    Cart createCart();

}
