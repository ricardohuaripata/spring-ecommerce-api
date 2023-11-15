package com.project.springecommerceapi.entity;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "size_color_product_variants")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SizeColorProductVariant {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "color_product_variant_id", referencedColumnName = "id", nullable = false)
    private ColorProductVariant colorProductVariant;

    @Column(name = "size", length = 3, nullable = false)
    @Check(constraints = "size IN ('S', 'M', 'L', 'XL', 'XXL')")
    private String size;

    @Column(name = "stock", nullable = false)
    private int stock;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date dateCreated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date dateLastModified;
}
