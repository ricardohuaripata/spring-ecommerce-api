package com.project.springecommerceapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.springecommerceapi.entity.Order;
import com.project.springecommerceapi.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findOrdersByUser(User user);

}
