package com.example.stickerstore.model.dto.response;

import com.example.stickerstore.model.entity.OrderProduct;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder()
public class OrderResponse {
    private String orderId;

    private Double totalPrice;

    private Long totalQuantity;

    private boolean purchased;
}
