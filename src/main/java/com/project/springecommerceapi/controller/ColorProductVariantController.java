package com.project.springecommerceapi.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.springecommerceapi.dto.ColorProductVariantDto;
import com.project.springecommerceapi.dto.UpdateColorProductVariantDto;
import com.project.springecommerceapi.entity.ColorProductVariant;
import com.project.springecommerceapi.entity.ProductImage;
import com.project.springecommerceapi.response.SuccessResponse;
import com.project.springecommerceapi.service.impl.ColorProductVariantServiceImpl;
import com.project.springecommerceapi.service.impl.ProductImageServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/color-product-variant")
@RequiredArgsConstructor
public class ColorProductVariantController {

        private final ColorProductVariantServiceImpl colorProductVariantService;
        private final ProductImageServiceImpl productImageService;

        @GetMapping("/{colorProductVariantId}")
        public ResponseEntity<?> getColorProductVariantById(
                        @PathVariable("colorProductVariantId") UUID colorProductVariantId) {

                ColorProductVariant colorProductVariant = colorProductVariantService
                                .getColorProductVariantById(colorProductVariantId);

                return new ResponseEntity<>(colorProductVariant, HttpStatus.OK);
        }

        @PutMapping("/{colorProductVariantId}")
        public ResponseEntity<?> updateColorProductVariantDetails(
                        @PathVariable("colorProductVariantId") UUID colorProductVariantId,
                        @RequestBody @Valid UpdateColorProductVariantDto updateColorProductVariantDto) {

                ColorProductVariant updatedColorProductVariant = colorProductVariantService
                                .updateColorProductVariantDetails(colorProductVariantId, updateColorProductVariantDto);

                return new ResponseEntity<>(updatedColorProductVariant, HttpStatus.OK);
        }

        @PatchMapping("/{colorProductVariantId}")
        public ResponseEntity<?> updateColorProductVariantMainImage(
                        @PathVariable("colorProductVariantId") UUID colorProductVariantId,
                        @RequestParam("image") MultipartFile image) {

                ColorProductVariant updatedColorProductVariant = colorProductVariantService
                                .updateColorProductVariantMainImage(colorProductVariantId, image);

                return new ResponseEntity<>(updatedColorProductVariant, HttpStatus.OK);
        }

        @PostMapping()
        public ResponseEntity<?> createColorProductVariant(
                        @RequestPart("data") @Valid ColorProductVariantDto colorProductVariantDto,
                        @RequestPart("image") MultipartFile image) {

                ColorProductVariant createdColorProductVariant = colorProductVariantService
                                .createColorProductVariant(colorProductVariantDto, image);

                return new ResponseEntity<>(createdColorProductVariant, HttpStatus.CREATED);
        }

        @GetMapping("/product/{productId}")
        public ResponseEntity<?> getColorProductVariantsByProduct(@PathVariable("productId") UUID productId) {

                List<ColorProductVariant> colorProductVariants = colorProductVariantService
                                .getColorProductVariantsByProduct(productId);

                return new ResponseEntity<>(colorProductVariants, HttpStatus.OK);
        }

        @GetMapping("/category/{categoryId}")
        public ResponseEntity<?> getColorProductVariantsByProductCategory(@PathVariable("categoryId") UUID categoryId,
                        @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                        @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {

                page = page != null && page >= 0 ? page : 0;
                size = size != null && size > 0 ? size : 5;

                Page<ColorProductVariant> colorProductVariants = colorProductVariantService
                                .getColorProductVariantsByProductCategoryPaginate(categoryId, page, size);

                return new ResponseEntity<>(colorProductVariants, HttpStatus.OK);
        }

        @PostMapping("/{colorProductVariantId}")
        public ResponseEntity<?> uploadProductImage(
                        @PathVariable("colorProductVariantId") UUID colorProductVariantId,
                        @RequestParam("image") MultipartFile image) {

                ProductImage productImage = productImageService.uploadProductImage(colorProductVariantId, image);

                return new ResponseEntity<>(productImage, HttpStatus.CREATED);
        }

        @DeleteMapping("/images/{productImageId}")
        public ResponseEntity<?> deleteProductImage(
                        @PathVariable("productImageId") Long productImageId) {

                String message = productImageService.deleteProductImage(productImageId);
                SuccessResponse successResponse = SuccessResponse.builder()
                                .type("Success")
                                .message(message)
                                .build();
                return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }

}
