package com.example.stickerstore.service;

import com.example.stickerstore.model.dto.request.OrderProductRequest;
import com.example.stickerstore.model.dto.response.OrderProductResponse;

import java.util.List;

public interface OrderProductService {
    OrderProductResponse createOrderProduct(OrderProductRequest orderProductRequest, Long productId);

    String deleteOrderProductById(Long orderProductId);

    List<OrderProductResponse> getAllOrderProductsByOrderId(Long orderId);

    List<OrderProductResponse> getAllCartProducts();
}
