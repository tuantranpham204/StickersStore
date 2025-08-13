package com.example.stickerstore.controller;

import com.example.stickerstore.model.dto.request.OrderProductRequest;
import com.example.stickerstore.model.dto.response.ApiResponse;
import com.example.stickerstore.model.entity.OrderProduct;
import com.example.stickerstore.repository.OrderProductRepository;
import com.example.stickerstore.repository.OrderRepository;
import com.example.stickerstore.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name="Order API")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "create empty cart")
    @PostMapping()
    public ResponseEntity<ApiResponse> createEmptyCart() {
        ApiResponse response = ApiResponse.succeed(orderService.createEmptyCart());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(summary = "purchase cart")
    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResponse> purchaseOrder(@PathVariable("orderId") Long orderId) {
        ApiResponse  response = ApiResponse.succeed(orderService.purchaseOrder(orderId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "get cart")
    @GetMapping("/cart")
    public ResponseEntity<ApiResponse> getCart() {
        ApiResponse response = ApiResponse.succeed(orderService.getCart());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
