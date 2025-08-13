package com.example.stickerstore.service;

import com.example.stickerstore.model.dto.request.OrderRequest;
import com.example.stickerstore.model.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse createEmptyCart();

    OrderResponse purchaseOrder(Long orderId);

    OrderResponse getCart();
}
