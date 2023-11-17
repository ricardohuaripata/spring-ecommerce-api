package com.project.springecommerceapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.springecommerceapi.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {

}
