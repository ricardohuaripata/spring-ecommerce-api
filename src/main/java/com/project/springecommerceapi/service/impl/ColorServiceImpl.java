package com.project.springecommerceapi.service.impl;

import com.project.springecommerceapi.service.IColorService;
import com.project.springecommerceapi.repository.ColorRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springecommerceapi.dto.ColorDto;
import com.project.springecommerceapi.entity.Color;
import com.project.springecommerceapi.exceptions.ColorNotFoundException;
import com.project.springecommerceapi.exceptions.HexcodeExistsException;
import com.project.springecommerceapi.exceptions.SlugExistsException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ColorServiceImpl implements IColorService {
    private final ColorRepository colorRepository;

    @Override
    public Color getColorById(UUID colorId) {
        return colorRepository.findById(colorId).orElseThrow(ColorNotFoundException::new);

    }

    @Override
    public Color getColorByHexcode(String hexcode) {
        return colorRepository.findByHexCode(hexcode).orElseThrow(ColorNotFoundException::new);

    }

    @Override
    public Color getColorBySlug(String slug) {
        return colorRepository.findBySlug(slug).orElseThrow(ColorNotFoundException::new);

    }

    @Override
    public Color createColor(ColorDto colorDto) {
        try {
            getColorBySlug(colorDto.getSlug());
            throw new SlugExistsException();

        } catch (ColorNotFoundException exception1) {

            try {
                getColorByHexcode(colorDto.getHexcode());
                throw new HexcodeExistsException();
            } catch (ColorNotFoundException exception2) {
                Color newColor = new Color();

                newColor.setTitle(colorDto.getTitle());
                newColor.setHexCode(colorDto.getHexcode());
                newColor.setSlug(colorDto.getSlug());
                Date currentDate = new Date();
                newColor.setDateCreated(currentDate);
                newColor.setDateLastModified(currentDate);

                return colorRepository.save(newColor);
            }

        }
    }

    @Override
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

}
