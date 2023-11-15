package com.project.springecommerceapi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springecommerceapi.dto.SizeColorProductVariantDto;
import com.project.springecommerceapi.entity.ColorProductVariant;
import com.project.springecommerceapi.entity.SizeColorProductVariant;
import com.project.springecommerceapi.exceptions.SizeColorProductVariantExistsException;
import com.project.springecommerceapi.exceptions.SizeColorProductVariantNotFoundException;
import com.project.springecommerceapi.service.ISizeColorProductVariantService;
import com.project.springecommerceapi.repository.SizeColorProductVariantRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SizeColorProductVariantServiceImpl implements ISizeColorProductVariantService {
    private final SizeColorProductVariantRepository sizeColorProductVariantRepository;
    private final ColorProductVariantServiceImpl colorProductVariantService;

    @Override
    public SizeColorProductVariant getSizeColorProductVariantById(UUID sizeColorProductVariantId) {
        return sizeColorProductVariantRepository.findById(sizeColorProductVariantId)
                .orElseThrow(SizeColorProductVariantNotFoundException::new);
    }

    @Override
    public List<SizeColorProductVariant> getSizeColorProductVariantsByColorProductVariant(
            ColorProductVariant colorProductVariant) {
        return sizeColorProductVariantRepository.findSizeColorProductVariantsByColorProductVariant(colorProductVariant);

    }

    @Override
    public SizeColorProductVariant createSizeColorProductVariant(
            SizeColorProductVariantDto sizeColorProductVariantDto) {

        ColorProductVariant colorProductVariant = colorProductVariantService
                .getColorProductVariantById(sizeColorProductVariantDto.getColorProductVariantId());

        // Verificar si ya existe una variación para el productoColor y la talla dada
        if (sizeColorProductVariantRepository.existsByColorProductVariantAndSize(colorProductVariant,
                sizeColorProductVariantDto.getSize())) {
            // Manejar el caso en el que ya existe una variación
            throw new SizeColorProductVariantExistsException();
        }

        SizeColorProductVariant newSizeColorProductVariant = new SizeColorProductVariant();

        newSizeColorProductVariant.setColorProductVariant(colorProductVariant);
        newSizeColorProductVariant.setSize(sizeColorProductVariantDto.getSize());
        newSizeColorProductVariant.setStock(sizeColorProductVariantDto.getStock());
        Date currentDate = new Date();
        newSizeColorProductVariant.setDateCreated(currentDate);
        newSizeColorProductVariant.setDateLastModified(currentDate);

        return sizeColorProductVariantRepository.save(newSizeColorProductVariant);
    }

}
