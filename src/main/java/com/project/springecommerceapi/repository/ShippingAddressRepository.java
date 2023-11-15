package com.project.springecommerceapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.springecommerceapi.entity.ShippingAddress;
import com.project.springecommerceapi.entity.User;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, UUID> {
    List<ShippingAddress> findShippingAddressesByUser(User user);

}
