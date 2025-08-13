package com.example.stickerstore.controller;

import com.example.stickerstore.model.dto.request.ProductRequest;
import com.example.stickerstore.model.dto.response.ApiResponse;
import com.example.stickerstore.model.dto.response.ProductResponse;
import com.example.stickerstore.model.entity.Product;
import com.example.stickerstore.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name="Product API")
//@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    public final ProductService productService;

    @Operation(summary = "create product")
    @PostMapping()
    public ResponseEntity<ApiResponse> addProduct(@RequestBody @Valid ProductRequest request) {
        ApiResponse response = ApiResponse.succeed(productService.createProduct(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "get all products")
    @GetMapping()
    public ResponseEntity<ApiResponse> getAllProducts() {
        ApiResponse response = ApiResponse.succeed(productService.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "get product by product id")
    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse> getProductByProductId(@PathVariable("productId") Long productId) {
        ApiResponse response = ApiResponse.succeed(productService.findProductByProductId(productId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
