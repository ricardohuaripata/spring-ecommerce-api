package com.project.springecommerceapi.service;

import java.util.List;
import java.util.UUID;

import com.project.springecommerceapi.dto.ShippingAddressDto;
import com.project.springecommerceapi.entity.ShippingAddress;

public interface IShippingAddressService {
    
    ShippingAddress getShippingAddressById(UUID shippingAddressId);

    ShippingAddress createNewShippingAddress(ShippingAddressDto shippingAddressDto);

    List<ShippingAddress> getShippingAddressListByAuthenticatedUser();

}
