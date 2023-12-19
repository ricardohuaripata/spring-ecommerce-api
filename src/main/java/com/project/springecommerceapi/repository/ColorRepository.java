package com.project.springecommerceapi.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.springecommerceapi.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, UUID> {
    Optional<Color> findByHexCode(String hexcode);
    Optional<Color> findBySlug(String slug);

}
