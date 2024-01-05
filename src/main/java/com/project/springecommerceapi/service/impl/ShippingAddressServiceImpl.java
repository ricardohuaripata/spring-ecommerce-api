package com.project.springecommerceapi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springecommerceapi.dto.ShippingAddressDto;
import com.project.springecommerceapi.dto.UpdateShippingAddressDto;
import com.project.springecommerceapi.entity.ShippingAddress;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.exceptions.InvalidOperationException;
import com.project.springecommerceapi.exceptions.ShippingAddressNotFoundException;
import com.project.springecommerceapi.mapper.MapstructMapperShippingAddressUpdate;
import com.project.springecommerceapi.repository.ShippingAddressRepository;
import com.project.springecommerceapi.service.IShippingAddressService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements IShippingAddressService {
    private final ShippingAddressRepository shippingAddressRepository;
    private final UserServiceImpl userService;
    private final MapstructMapperShippingAddressUpdate mapstructMapperShippingAddressUpdate;

    @Override
    public ShippingAddress getShippingAddressById(UUID shippingAddressId) {
        return shippingAddressRepository.findById(shippingAddressId).orElseThrow(ShippingAddressNotFoundException::new);
    }

    @Override
    public ShippingAddress createNewShippingAddress(ShippingAddressDto shippingAddressDto) {

        User user = userService.getAuthenticatedUser();

        ShippingAddress newShippingAddress = new ShippingAddress();

        newShippingAddress.setUser(user);
        newShippingAddress.setFirstName(shippingAddressDto.getFirstName());
        newShippingAddress.setLastName(shippingAddressDto.getLastName());
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
    public List<ShippingAddress> getAuthenticatedUserShippingAddressList() {
        User authUser = userService.getAuthenticatedUser();
        return authUser.getShippingAddressList();
    }

    @Override
    public ShippingAddress updateShippingAddressDetails(UUID shippingAddressId,
            UpdateShippingAddressDto updateShippingAddressDto) {

        User authUser = userService.getAuthenticatedUser();

        ShippingAddress shippingAddress = getShippingAddressById(shippingAddressId);

        if (shippingAddress.getUser().equals(authUser)) {
            mapstructMapperShippingAddressUpdate.updateShippingAddressFromUpdateShippingAddressDto(
                    updateShippingAddressDto, shippingAddress);
            shippingAddress.setDateLastModified(new Date());

            return shippingAddressRepository.save(shippingAddress);
        } else {
            throw new InvalidOperationException();
        }

    }

    @Override
    public void deleteShippingAddress(UUID shippingAddressId) {
        User authUser = userService.getAuthenticatedUser();
        ShippingAddress shippingAddress = getShippingAddressById(shippingAddressId);

        if (shippingAddress.getUser().equals(authUser)) {
            shippingAddressRepository.delete(shippingAddress);

        } else {
            throw new InvalidOperationException();
        }
    }

}
