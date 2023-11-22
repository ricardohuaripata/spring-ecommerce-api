package com.project.springecommerceapi.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springecommerceapi.dto.OrderDto;
import com.project.springecommerceapi.entity.Cart;
import com.project.springecommerceapi.entity.CartItem;
import com.project.springecommerceapi.entity.Order;
import com.project.springecommerceapi.entity.OrderItem;
import com.project.springecommerceapi.entity.SizeColorProductVariant;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.exceptions.NoItemsToPayException;
import com.project.springecommerceapi.exceptions.OrderNotFoundException;
import com.project.springecommerceapi.exceptions.PaymentTransactionFailedException;
import com.project.springecommerceapi.exceptions.VerifiedEmailRequiredException;
import com.project.springecommerceapi.repository.OrderItemRepository;
import com.project.springecommerceapi.repository.OrderRepository;
import com.project.springecommerceapi.repository.SizeColorProductVariantRepository;
import com.project.springecommerceapi.service.IOrderService;

import lombok.RequiredArgsConstructor;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    @Value("${stripe_secret_key}")
    private String stripeSecretKey;

    // Configurar la clave secreta de Stripe
    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartServiceImpl cartService;
    private final SizeColorProductVariantRepository sizeColorProductVariantRepository;

    @Override
    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public Order createOrder(User user, OrderDto orderDto) {

        Cart cart = cartService.getCartById(orderDto.getCartId());
        List<CartItem> itemList = cart.getCartItems();

        if (itemList == null || itemList.isEmpty()) {
            throw new NoItemsToPayException();
        }

        if (user.getEmailVerified() == false) {
            throw new VerifiedEmailRequiredException();
        }

        BigDecimal totalAmount = BigDecimal.ZERO; // Inicializar el total en 0

        for (CartItem item : itemList) {
            BigDecimal itemPrice = item.getSizeColorProductVariant().getColorProductVariant().getFinalPrice();
            totalAmount = totalAmount.add(itemPrice); // Sumar el precio del artículo al total
        }

        // Crear la transacción en Stripe
        ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount(totalAmount.multiply(new BigDecimal(100)).longValue())
                .setCurrency("eur")
                .setSource(orderDto.getCardToken())
                .build();

        Charge charge;
        try {
            charge = Charge.create(params);
        } catch (StripeException e) {
            e.printStackTrace();
            throw new PaymentTransactionFailedException();
        }

        Order newOrder = new Order();
        newOrder.setUser(user);
        newOrder.setStatus("pending");
        newOrder.setTransactionId(charge.getId());
        newOrder.setFirstname(orderDto.getFirstName());
        newOrder.setLastname(orderDto.getLastName());
        newOrder.setCountry(orderDto.getCountry());
        newOrder.setCity(orderDto.getCity());
        newOrder.setPostalCode(orderDto.getPostalCode());
        newOrder.setAddress(orderDto.getAddress());
        newOrder.setContactPhone(orderDto.getContactPhone());

        Date currentDate = new Date();
        newOrder.setDateCreated(currentDate);
        newOrder.setDateLastModified(currentDate);

        Order createdOrder = orderRepository.save(newOrder);

        for (CartItem item : itemList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(createdOrder);
            orderItem.setSizeColorProductVariant(item.getSizeColorProductVariant());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setUnitPrice(item.getSizeColorProductVariant().getColorProductVariant().getFinalPrice());
            orderItemRepository.save(orderItem);

            SizeColorProductVariant sizeColorProductVariant = item.getSizeColorProductVariant();
            sizeColorProductVariant.setStock(sizeColorProductVariant.getStock() - item.getQuantity());
            sizeColorProductVariant.setDateLastModified(currentDate);
            sizeColorProductVariantRepository.save(sizeColorProductVariant);

        }

        cartService.clearCart(cart.getId());

        return createdOrder;
    }

}
