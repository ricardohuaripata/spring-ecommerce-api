package com.project.springecommerceapi.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springecommerceapi.common.AppConstants;
import com.project.springecommerceapi.dto.OrderDto;
import com.project.springecommerceapi.entity.Cart;
import com.project.springecommerceapi.entity.CartItem;
import com.project.springecommerceapi.entity.Order;
import com.project.springecommerceapi.entity.OrderItem;
import com.project.springecommerceapi.entity.SizeColorProductVariant;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.enumeration.Role;
import com.project.springecommerceapi.exceptions.InvalidOperationException;
import com.project.springecommerceapi.exceptions.NoItemsToPayException;
import com.project.springecommerceapi.exceptions.OrderNotFoundException;
import com.project.springecommerceapi.exceptions.PaymentTransactionFailedException;
import com.project.springecommerceapi.exceptions.VerifiedEmailRequiredException;
import com.project.springecommerceapi.repository.OrderItemRepository;
import com.project.springecommerceapi.repository.OrderRepository;
import com.project.springecommerceapi.repository.SizeColorProductVariantRepository;
import com.project.springecommerceapi.response.OrderResponse;
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
    private final UserServiceImpl userService;
    private final EmailServiceImpl emailService;

    @Override
    public OrderResponse getOrder(UUID orderId) {
        User authUser = userService.getAuthenticatedUser();
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        // solo el usuario correspondiente al pedido o un usuario admin
        if (order.getUser().equals(authUser) || authUser.getRole().equals(Role.ROLE_ADMIN.name())) {

            BigDecimal totalAmount = BigDecimal.ZERO; // Inicializar el total en 0
            int totalItems = 0;

            for (OrderItem item : order.getOrderItems()) {
                BigDecimal itemPrice = item.getSizeColorProductVariant().getColorProductVariant().getFinalPrice();
                totalAmount = totalAmount.add(itemPrice); // Sumar el precio del artículo al total
                totalItems += item.getQuantity();
            }

            OrderResponse orderResponse = OrderResponse.builder()
                    .id(order.getId())
                    .user(order.getUser())
                    .status(order.getStatus())
                    .chargeId(order.getChargeId())
                    .firstName(order.getFirstName())
                    .lastName(order.getLastName())
                    .country(order.getCountry())
                    .city(order.getCity())
                    .postalCode(order.getPostalCode())
                    .address(order.getAddress())
                    .contactPhone(order.getContactPhone())
                    .orderDate(order.getDateCreated())
                    .orderItems(order.getOrderItems())
                    .totalQuantity(totalItems)
                    .totalAmount(totalAmount)
                    .build();

            return orderResponse;

        } else {
            throw new InvalidOperationException();
        }
    }

    @Override
    public OrderResponse createOrder(OrderDto orderDto) {
        User user = userService.getAuthenticatedUser();

        Cart cart = cartService.getCartById(orderDto.getCartId());
        List<CartItem> itemList = cart.getCartItems();

        if (itemList == null || itemList.isEmpty()) {
            throw new NoItemsToPayException();
        }

        if (user.getEmailVerified() == false) {
            throw new VerifiedEmailRequiredException();
        }

        BigDecimal totalAmount = BigDecimal.ZERO; // Inicializar el total en 0
        int totalItems = 0;

        for (CartItem item : itemList) {
            BigDecimal itemPrice = item.getSizeColorProductVariant().getColorProductVariant().getFinalPrice();
            totalAmount = totalAmount.add(itemPrice); // Sumar el precio del artículo al total
            totalItems += item.getQuantity();
        }

        // Crear la transacción en Stripe

        Map<String, String> metadata = new HashMap<>();
        metadata.put("user_id", user.getId().toString());
        metadata.put("firstName", orderDto.getFirstName());
        metadata.put("lastName", orderDto.getLastName());
        metadata.put("country", orderDto.getCountry());
        metadata.put("city", orderDto.getCity());
        metadata.put("postalCode", orderDto.getPostalCode());
        metadata.put("address", orderDto.getAddress());
        metadata.put("contactPhone", orderDto.getContactPhone());

        ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount(totalAmount.multiply(new BigDecimal(100)).longValue())
                .setCurrency("eur")
                .setSource(orderDto.getCardToken())
                .setDescription("Transacción de pago por valor de " + totalAmount + " por la compra de " + totalItems
                        + " artículos.")
                .setMetadata(metadata)
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
        newOrder.setChargeId(charge.getId());
        newOrder.setFirstName(orderDto.getFirstName());
        newOrder.setLastName(orderDto.getLastName());
        newOrder.setCountry(orderDto.getCountry());
        newOrder.setCity(orderDto.getCity());
        newOrder.setPostalCode(orderDto.getPostalCode());
        newOrder.setAddress(orderDto.getAddress());
        newOrder.setContactPhone(orderDto.getContactPhone());

        Date currentDate = new Date();
        newOrder.setDateCreated(currentDate);
        newOrder.setDateLastModified(currentDate);

        Order createdOrder = orderRepository.save(newOrder);

        List<OrderItem> createdOrderItems = new ArrayList<OrderItem>();
        for (CartItem item : itemList) {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(createdOrder);
            orderItem.setSizeColorProductVariant(item.getSizeColorProductVariant());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setUnitPrice(item.getSizeColorProductVariant().getColorProductVariant().getFinalPrice());

            OrderItem createdOrderItem = orderItemRepository.save(orderItem);
            createdOrderItems.add(createdOrderItem);

            SizeColorProductVariant sizeColorProductVariant = item.getSizeColorProductVariant();
            sizeColorProductVariant.setStock(sizeColorProductVariant.getStock() - item.getQuantity());
            sizeColorProductVariant.setDateLastModified(currentDate);
            sizeColorProductVariantRepository.save(sizeColorProductVariant);

        }

        cartService.clearCart(cart.getId());

        OrderResponse orderResponse = OrderResponse.builder()
                .id(createdOrder.getId())
                .user(createdOrder.getUser())
                .status(createdOrder.getStatus())
                .chargeId(createdOrder.getChargeId())
                .firstName(createdOrder.getFirstName())
                .lastName(createdOrder.getLastName())
                .country(createdOrder.getCountry())
                .city(createdOrder.getCity())
                .postalCode(createdOrder.getPostalCode())
                .address(createdOrder.getAddress())
                .contactPhone(createdOrder.getContactPhone())
                .orderDate(createdOrder.getDateCreated())
                .orderItems(createdOrderItems)
                .totalQuantity(totalItems)
                .totalAmount(totalAmount)
                .build();
        // Enviar resumen del pedido al email del usuario
        String emailSuccessfulOrder = emailService.buildOrderSuccessDetailsMail(orderResponse);
        emailService.send(user.getEmail(), AppConstants.SUCCESSFUL_ORDER, emailSuccessfulOrder);

        return orderResponse;
    }

}
