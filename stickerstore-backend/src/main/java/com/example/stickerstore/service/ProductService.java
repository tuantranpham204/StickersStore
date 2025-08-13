package com.example.stickerstore.service;

import com.example.stickerstore.model.dto.request.ProductRequest;
import com.example.stickerstore.model.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);

    List<ProductResponse> findAll();

    ProductResponse findProductByProductId(Long productId);
}
