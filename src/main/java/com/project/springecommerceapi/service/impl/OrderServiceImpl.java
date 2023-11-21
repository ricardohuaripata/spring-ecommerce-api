package com.project.springecommerceapi.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springecommerceapi.dto.OrderDto;
import com.project.springecommerceapi.entity.Cart;
import com.project.springecommerceapi.entity.CartItem;
import com.project.springecommerceapi.entity.Order;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.exceptions.OrderNotFoundException;
import com.project.springecommerceapi.repository.OrderItemRepository;
import com.project.springecommerceapi.repository.OrderRepository;
import com.project.springecommerceapi.service.IOrderService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    @Value("${stripe_secret_key}")
    private String stripeSecretKey;

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final SizeColorProductVariantServiceImpl sizeColorProductVariantService;
    private final CartServiceImpl cartService;

    @Override
    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public Order createOrder(User user, OrderDto orderDto) {

        Cart cart = cartService.getCartById(orderDto.getCartId());

        BigDecimal totalAmount = BigDecimal.ZERO; // Inicializar el total en 0
        List<CartItem> itemList = cart.getCartItems();

        for (CartItem item : itemList) {
            BigDecimal itemPrice = item.getSizeColorProductVariant().getColorProductVariant().getFinalPrice();
            totalAmount = totalAmount.add(itemPrice); // Sumar el precio del artículo al total
        }

        Order newOrder = new Order();
        newOrder.setUser(user);
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOrder'");
    }

}
