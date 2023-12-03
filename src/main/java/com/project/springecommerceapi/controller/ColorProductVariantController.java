package com.project.springecommerceapi.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.springecommerceapi.dto.ColorProductVariantDto;
import com.project.springecommerceapi.entity.Category;
import com.project.springecommerceapi.entity.ColorProductVariant;
import com.project.springecommerceapi.entity.Product;
import com.project.springecommerceapi.entity.ProductImage;
import com.project.springecommerceapi.service.impl.CategoryServiceImpl;
import com.project.springecommerceapi.service.impl.ColorProductVariantServiceImpl;
import com.project.springecommerceapi.service.impl.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/color-product-variant")
@RequiredArgsConstructor
public class ColorProductVariantController {

        private final ProductServiceImpl productService;
        private final ColorProductVariantServiceImpl colorProductVariantService;
        private final CategoryServiceImpl categoryService;

        @GetMapping("/{colorProductVariantId}")
        public ResponseEntity<?> getColorProductVariantById(
                        @PathVariable("colorProductVariantId") UUID colorProductVariantId) {

                ColorProductVariant colorProductVariant = colorProductVariantService
                                .getColorProductVariantById(colorProductVariantId);

                return new ResponseEntity<>(colorProductVariant, HttpStatus.OK);
        }

        @PostMapping()
        public ResponseEntity<?> createColorProductVariant(
                        @RequestBody @Valid ColorProductVariantDto colorProductVariantDto) {

                ColorProductVariant createdColorProductVariant = colorProductVariantService
                                .createColorProductVariant(colorProductVariantDto);

                return new ResponseEntity<>(createdColorProductVariant, HttpStatus.CREATED);
        }

        @GetMapping("/product/{productId}")
        public ResponseEntity<?> getColorProductVariantsByProduct(@PathVariable("productId") UUID productId) {
                Product product = productService.getProductById(productId);

                List<ColorProductVariant> colorProductVariants = colorProductVariantService
                                .getColorProductVariantsByProduct(product);

                return new ResponseEntity<>(colorProductVariants, HttpStatus.OK);
        }

        @GetMapping("/category/{categoryId}")
        public ResponseEntity<?> getColorProductVariantsByProductCategory(@PathVariable("categoryId") UUID categoryId,
                        @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                        @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {

                page = page != null && page >= 0 ? page : 0;
                size = size != null && size > 0 ? size : 5;

                Category category = categoryService.getCategoryById(categoryId);

                Page<ColorProductVariant> colorProductVariants = colorProductVariantService
                                .getColorProductVariantsByProductCategoryPaginate(category, page, size);

                return new ResponseEntity<>(colorProductVariants, HttpStatus.OK);
        }

        @PostMapping("/{colorProductVariantId}")
        public ResponseEntity<?> uploadProductImage(
                        @PathVariable("colorProductVariantId") UUID colorProductVariantId,
                        @RequestParam(value = "file") MultipartFile file,
                        @RequestParam(value = "orderPosition") int orderPosition) {

                ProductImage productImage = colorProductVariantService.uploadProductImage(colorProductVariantId, file,
                                orderPosition);

                return new ResponseEntity<>(productImage, HttpStatus.CREATED);
        }

}
