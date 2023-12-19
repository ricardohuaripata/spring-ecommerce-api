package com.project.springecommerceapi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springecommerceapi.common.AppConstants;
import com.project.springecommerceapi.dto.CartItemDto;
import com.project.springecommerceapi.entity.Cart;
import com.project.springecommerceapi.entity.CartItem;
import com.project.springecommerceapi.entity.SizeColorProductVariant;
import com.project.springecommerceapi.exceptions.CartItemQuantityLimitReachedException;
import com.project.springecommerceapi.exceptions.CartItemNotFoundException;
import com.project.springecommerceapi.exceptions.CartNotFoundException;
import com.project.springecommerceapi.exceptions.NotEnoughStockException;
import com.project.springecommerceapi.repository.CartItemRepository;
import com.project.springecommerceapi.repository.CartRepository;
import com.project.springecommerceapi.service.ICartService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final SizeColorProductVariantServiceImpl sizeColorProductVariantService;

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
    public CartItem getCartItemById(UUID cartItemId) {
        return cartItemRepository.findById(cartItemId).orElseThrow(CartItemNotFoundException::new);
    }

    @Override
    public Cart addToCart(UUID cartId, CartItemDto cartItemDto) {

        Cart cart = getCartById(cartId);

        SizeColorProductVariant sizeColorProductVariant = sizeColorProductVariantService
                .getSizeColorProductVariantById(cartItemDto.getSizeColorProductVariantId());

        CartItem cartItem = cartItemRepository.findByCartAndSizeColorProductVariant(cart, sizeColorProductVariant);

        // Si el artículo ya existe en el carrito solo se le incrementa la cantidad
        if (cartItem != null) {
            int newQuantity = cartItem.getQuantity() + 1;
            // Establecer un limite de cantidad de 10 por articulo
            if (newQuantity > 10) {
                throw new CartItemQuantityLimitReachedException();
            }
            // Comprobar que hay suficiente stock antes de incrementar la cantidad
            if (sizeColorProductVariant.getStock() < newQuantity) {
                throw new NotEnoughStockException();
            }
            cartItem.setQuantity(newQuantity);
            cartItemRepository.save(cartItem);

            return refreshCart(cart);
        }

        // Comprobar que hay suficiente stock antes de añadir el artículo
        if (sizeColorProductVariant.getStock() < 1) {
            throw new NotEnoughStockException();
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setSizeColorProductVariant(sizeColorProductVariant);
        newCartItem.setQuantity(1);
        cartItemRepository.save(newCartItem);
        return refreshCart(cart);
    }

    @Override
    public Cart deleteCartItem(UUID cartItemId) {
        CartItem cartItem = getCartItemById(cartItemId);
        cartItemRepository.delete(cartItem);
        Cart cart = getCartById(cartItem.getCart().getId());
        return refreshCart(cart);

    }

    private Cart refreshCart(Cart cart) {
        cart.setDateLastModified(new Date());
        cart.setDateExpiration(new Date(System.currentTimeMillis() + AppConstants.CART_EXPIRATION));
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(UUID cartId) {
        Cart cart = getCartById(cartId);
        List<CartItem> itemList = cart.getCartItems();

        for (CartItem item : itemList) {
            cartItemRepository.delete(item);
        }
        refreshCart(cart);
    }

    @Override
    public int deleteExpiredCarts() {
        List<Cart> expiredCartList = cartRepository.findAllExpiredCarts(new Date());
        for (Cart expiredCart : expiredCartList) {
            cartRepository.delete(expiredCart);
        }
        return expiredCartList.size();
    }

}
