package com.project.springecommerceapi.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "status", length = 64, nullable = false)
    @Check(constraints = "size IN ('pending', 'sent', 'delivered')")
    private String status;

    @Column(name = "transaction_id", length = 128, nullable = false)
    private String transactionId;

    @Column(length = 64, nullable = false)
    private String firstname;

    @Column(length = 64, nullable = false)
    private String lastname;

    @Column(length = 64, nullable = false)
    private String country;

    @Column(length = 64, nullable = false)
    private String city;

    @Column(length = 10, nullable = false)
    private String postalCode;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String address;

    @Column(length = 20, nullable = false)
    private String contactPhone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date dateCreated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date dateLastModified;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<OrderItem> orderItems;

}
