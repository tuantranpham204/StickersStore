package com.example.stickerstore.controller;

import com.example.stickerstore.model.dto.request.OrderProductRequest;
import com.example.stickerstore.model.dto.response.ApiResponse;
import com.example.stickerstore.model.dto.response.OrderProductResponse;
import com.example.stickerstore.repository.OrderProductRepository;
import com.example.stickerstore.service.OrderProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/order-products")
@RequiredArgsConstructor
@Tag(name="Order product API")
public class OrderProductController {
    private final OrderProductService orderProductService;

    @Operation(summary = "create or update order product in cart")
    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse> createOrderProduct(@RequestBody @Valid OrderProductRequest orderProductRequest,
                                                          @PathVariable("productId") Long productId) {
        ApiResponse response = ApiResponse.succeed(orderProductService.createOrderProduct(orderProductRequest, productId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "delete order product")
    @DeleteMapping("/{orderProductId}")
    public ResponseEntity<ApiResponse> deleteOrderProductById(@PathVariable("orderProductId") Long orderProductId) {
        ApiResponse response = ApiResponse.succeed(orderProductService.deleteOrderProductById(orderProductId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "get all order product by order id")
    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse> getAllOrderProductsByOrderId(@PathVariable("orderId") Long orderId) {
        ApiResponse response = ApiResponse.succeed(orderProductService.getAllOrderProductsByOrderId(orderId));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Operation(summary = "get all cart products")
    @GetMapping("/cart")
    public ResponseEntity<ApiResponse> getAllCartProducts() {
        ApiResponse response = ApiResponse.succeed(orderProductService.getAllCartProducts());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

+

}
