package com.project.springecommerceapi.service;

import java.util.List;
import java.util.UUID;

import com.project.springecommerceapi.dto.ShippingAddressDto;
import com.project.springecommerceapi.entity.ShippingAddress;
import com.project.springecommerceapi.entity.User;

public interface IShippingAddressService {
    
    ShippingAddress getShippingAddressById(UUID shippingAddressId);

    ShippingAddress createNewShippingAddress(User user, ShippingAddressDto shippingAddressDto);

    List<ShippingAddress> getShippingAddressListByUser(User user);

}
