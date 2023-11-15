package com.project.springecommerceapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.springecommerceapi.entity.Category;
import com.project.springecommerceapi.entity.Color;
import com.project.springecommerceapi.entity.ColorProductVariant;
import com.project.springecommerceapi.entity.Product;

@Repository
public interface ColorProductVariantRepository extends JpaRepository<ColorProductVariant, UUID> {
    List<ColorProductVariant> findColorProductVariantsByProduct(Product product);

    boolean existsByProductAndColor(Product product, Color color);

    Page<ColorProductVariant> findColorProductVariantsByProduct_Category(Category category, Pageable pageable);

}
