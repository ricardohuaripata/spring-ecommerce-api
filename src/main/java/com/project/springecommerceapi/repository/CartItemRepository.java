package com.project.springecommerceapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.springecommerceapi.entity.Cart;
import com.project.springecommerceapi.entity.CartItem;
import com.project.springecommerceapi.entity.SizeColorProductVariant;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    CartItem findByCartAndSizeColorProductVariant(Cart cart, SizeColorProductVariant sizeColorProductVariant);

}
