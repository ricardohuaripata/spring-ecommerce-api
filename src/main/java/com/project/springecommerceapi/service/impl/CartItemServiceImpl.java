package com.project.springecommerceapi.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springecommerceapi.dto.CartItemDto;
import com.project.springecommerceapi.entity.Cart;
import com.project.springecommerceapi.entity.CartItem;
import com.project.springecommerceapi.entity.SizeColorProductVariant;
import com.project.springecommerceapi.exceptions.CartItemNotFoundException;
import com.project.springecommerceapi.exceptions.NotEnoughStockException;
import com.project.springecommerceapi.repository.CartItemRepository;
import com.project.springecommerceapi.service.ICartItemService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CartItemServiceImpl implements ICartItemService {

    private final CartItemRepository cartItemRepository;
    private final SizeColorProductVariantServiceImpl sizeColorProductVariantService;

    @Override
    public CartItem getCartItemById(UUID cartItemId) {
        return cartItemRepository.findById(cartItemId).orElseThrow(CartItemNotFoundException::new);

    }

    @Override
    public CartItem createCartItem(Cart cart, CartItemDto cartItemDto) {

        SizeColorProductVariant sizeColorProductVariant = sizeColorProductVariantService
                .getSizeColorProductVariantById(cartItemDto.getSizeColorProductVariantId());

        CartItem cartItem = cartItemRepository.findByCartAndSizeColorProductVariant(cart, sizeColorProductVariant);

        // Si el artículo ya existe en el carrito solo se le incrementa la cantidad
        if (cartItem != null) {

            int newQuantity = cartItem.getQuantity() + 1;

            // Comprobar que hay suficiente stock antes de incrementar la cantidad
            if (sizeColorProductVariant.getStock() < newQuantity) {
                throw new NotEnoughStockException();
            }

            cartItem.setQuantity(newQuantity);
            return cartItemRepository.save(cartItem);
        }

        // Comprobar que hay suficiente stock antes de añadir el artículo
        if (sizeColorProductVariant.getStock() < 1) {
            throw new NotEnoughStockException();
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setSizeColorProductVariant(sizeColorProductVariant);
        newCartItem.setQuantity(1);

        return cartItemRepository.save(newCartItem);
    }

    @Override
    public void deleteCartItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

}
