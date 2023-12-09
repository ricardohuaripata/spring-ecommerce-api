package com.project.springecommerceapi.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.springecommerceapi.entity.OrderItem;
import com.project.springecommerceapi.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
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

}
