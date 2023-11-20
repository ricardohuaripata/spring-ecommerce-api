package com.project.springecommerceapi.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springecommerceapi.common.AppConstants;
import com.project.springecommerceapi.entity.Cart;
import com.project.springecommerceapi.exceptions.CartNotFoundException;
import com.project.springecommerceapi.repository.CartRepository;
import com.project.springecommerceapi.service.ICartService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {

    private final CartRepository cartRepository;

    @Override
    public Cart getCartById(UUID cartId) {
        return cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
    }

    @Override
    public Cart createCart() {

        Cart newCart = new Cart();
        Date currentDate = new Date();
        Date expirationDate = new Date(System.currentTimeMillis() + AppConstants.CART_EXPIRATION);

        newCart.setDateCreated(currentDate);
        newCart.setDateLastModified(currentDate);
        newCart.setDateExpiration(expirationDate);

        return cartRepository.save(newCart);

    }

    @Override
    public Cart refreshCart(Cart cart) {
        cart.setDateLastModified(new Date());
        cart.setDateExpiration(new Date(System.currentTimeMillis() + AppConstants.CART_EXPIRATION));
        return cartRepository.save(cart);
    }

}
