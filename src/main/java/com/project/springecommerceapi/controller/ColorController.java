package com.project.springecommerceapi.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springecommerceapi.dto.ColorDto;
import com.project.springecommerceapi.entity.Color;
import com.project.springecommerceapi.service.impl.ColorServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/color")
@RequiredArgsConstructor
public class ColorController {

    private final ColorServiceImpl colorService;

    @GetMapping()
    public ResponseEntity<?> getAllCategories() {
        List<Color> colorList = colorService.getAllColors();
        return new ResponseEntity<>(colorList, HttpStatus.OK);
    }

    @GetMapping("/{colorId}")
    public ResponseEntity<?> getColorById(@PathVariable("colorId") UUID colorId) {
        Color colorToFind = colorService.getColorById(colorId);
        return new ResponseEntity<>(colorToFind, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createColor(@RequestBody @Valid ColorDto colorDto) {
        Color createdColor = colorService.createColor(colorDto);
        return new ResponseEntity<>(createdColor, HttpStatus.CREATED);
    }
}
