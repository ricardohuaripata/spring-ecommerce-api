package com.project.springecommerceapi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springecommerceapi.dto.ShippingAddressDto;
import com.project.springecommerceapi.entity.ShippingAddress;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.repository.ShippingAddressRepository;
import com.project.springecommerceapi.service.IShippingAddressService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements IShippingAddressService {
    private final ShippingAddressRepository shippingAddressRepository;

    @Override
    public ShippingAddress getShippingAddressById(UUID shippingAddressId) {
        return shippingAddressRepository.findById(shippingAddressId).orElseThrow(null);
    }

    @Override
    public ShippingAddress createNewShippingAddress(User user, ShippingAddressDto shippingAddressDto) {
        ShippingAddress newShippingAddress = new ShippingAddress();

        newShippingAddress.setUser(user);
        newShippingAddress.setFirstname(shippingAddressDto.getFirstName());
        newShippingAddress.setLastname(shippingAddressDto.getLastName());
        newShippingAddress.setCountry(shippingAddressDto.getCountry());
        newShippingAddress.setCity(shippingAddressDto.getCity());
        newShippingAddress.setPostalCode(shippingAddressDto.getPostalCode());
        newShippingAddress.setAddress(shippingAddressDto.getAddress());
        newShippingAddress.setContactPhone(shippingAddressDto.getContactPhone());

        newShippingAddress.setDateCreated(new Date());
        newShippingAddress.setDateLastModified(new Date());

        return shippingAddressRepository.save(newShippingAddress);
    }

    @Override
    public List<ShippingAddress> getShippingAddressListByUser(User user) {
        return shippingAddressRepository.findShippingAddressesByUser(user);
    }

}