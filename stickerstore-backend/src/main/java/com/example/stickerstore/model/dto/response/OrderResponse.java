package com.example.stickerstore.model.dto.response;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder()
public class OrderResponse {
    private Long orderId;

    private Double totalPrice;

    private Long totalQuantity;

    private boolean purchased;
}
