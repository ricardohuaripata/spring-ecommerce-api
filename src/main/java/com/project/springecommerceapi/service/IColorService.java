package com.project.springecommerceapi.service;

import java.util.List;
import java.util.UUID;

import com.project.springecommerceapi.dto.ColorDto;
import com.project.springecommerceapi.entity.Color;

public interface IColorService {
    
    Color getColorById(UUID colorId);

    Color getColorByHexcode(String hexcode);

    Color createColor(ColorDto colorDto);

    List<Color> getAllColors();
}
