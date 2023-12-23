package com.project.springecommerceapi.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.springecommerceapi.entity.Order;
import com.project.springecommerceapi.entity.OrderItem;
import com.project.springecommerceapi.entity.User;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderResponse {
    private UUID id;
    private User user;
    private String status;
    private String chargeId;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String postalCode;
    private String address;
    private String contactPhone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date orderDate;
    private List<OrderItem> orderItems;
    private int totalQuantity;
    private BigDecimal totalAmount;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.user = order.getUser();
        this.status = order.getStatus();
        this.chargeId = order.getChargeId();
        this.firstName = order.getFirstName();
        this.lastName = order.getLastName();
        this.country = order.getCountry();
        this.city = order.getCity();
        this.postalCode = order.getPostalCode();
        this.address = order.getAddress();
        this.contactPhone = order.getContactPhone();
        this.orderDate = order.getDateCreated();

        BigDecimal totalAmount = BigDecimal.ZERO;
        int totalItems = 0;

        for (OrderItem item : order.getOrderItems()) {
            BigDecimal itemUnitPrice = item.getUnitPrice();
            BigDecimal itemTotalPrice = itemUnitPrice.multiply(BigDecimal.valueOf(item.getQuantity()));

            totalAmount = totalAmount.add(itemTotalPrice);
            totalItems += item.getQuantity();
        }

        this.orderItems = order.getOrderItems();
        this.totalQuantity = totalItems;
        this.totalAmount = totalAmount;
    }

}
