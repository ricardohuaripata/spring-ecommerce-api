package com.project.springecommerceapi.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.springecommerceapi.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    @Query("SELECT c FROM Cart c WHERE c.dateExpiration < :currentDate")
    List<Cart> findAllExpiredCarts(Date currentDate);
}
