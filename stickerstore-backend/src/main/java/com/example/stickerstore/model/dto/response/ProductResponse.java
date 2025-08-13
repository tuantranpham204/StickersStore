package com.example.stickerstore.model.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder()
public class ProductResponse {
    private Long productId;
    private String productName;
    private String productDescription;
    private Double productUnitPrice;
    private String productImageUrl;
}
