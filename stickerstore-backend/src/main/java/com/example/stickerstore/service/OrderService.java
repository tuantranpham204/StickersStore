package com.example.stickerstore.service;

import com.example.stickerstore.model.dto.request.OrderRequest;
import com.example.stickerstore.model.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse createEmptyCart();

    OrderResponse purchaseOrder();

    OrderResponse getCart();

    OrderResponse getOrderById(Long orderId);
}
