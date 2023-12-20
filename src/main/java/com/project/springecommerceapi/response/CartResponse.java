package com.project.springecommerceapi.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.project.springecommerceapi.entity.Cart;
import com.project.springecommerceapi.entity.CartItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponse {
    private UUID id;
    private Date dateCreated;
    private Date dateLastModified;
    private Date dateExpiration;
    private List<CartItem> cartItems;
    private BigDecimal totalAmount;
    private int totalQuantity;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.dateCreated = cart.getDateCreated();
        this.dateLastModified = cart.getDateLastModified();
        this.dateExpiration = cart.getDateExpiration();

        BigDecimal totalAmount = BigDecimal.ZERO;
        int totalItems = 0;

        if (cart.getCartItems() != null) {
            for (CartItem item : cart.getCartItems()) {
                BigDecimal itemUnitPrice = item.getSizeColorProductVariant().getColorProductVariant().getFinalPrice();
                BigDecimal itemTotalPrice = itemUnitPrice.multiply(BigDecimal.valueOf(item.getQuantity()));

                totalAmount = totalAmount.add(itemTotalPrice);
                totalItems += item.getQuantity();

            }

            this.cartItems = cart.getCartItems();

        } else {
            this.cartItems = new ArrayList<CartItem>();
        }

        this.totalAmount = totalAmount;
        this.totalQuantity = totalItems;

    }
}
