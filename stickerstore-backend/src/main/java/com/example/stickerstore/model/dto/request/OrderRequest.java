package com.example.stickerstore.model.dto.request;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder()
public class OrderRequest {
    private Double totalPrice;

    private Long totalQuantity;

    private Set<OrderProductRequest> orderProducts;

}
