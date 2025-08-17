package com.example.stickerstore.model.dto.response;

import com.example.stickerstore.model.entity.Order;
import com.example.stickerstore.model.entity.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder()
public class OrderProductResponse {
    private Long orderProductId;

    private Order order;

    private Product product;

    private Long subQuantity = 0l;
}
