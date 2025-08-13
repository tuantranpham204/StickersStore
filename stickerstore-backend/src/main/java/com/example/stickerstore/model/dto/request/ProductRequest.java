package com.example.stickerstore.model.dto.request;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder()
public class ProductRequest {
    private String productName;
    private String productDescription;
    private Double productUnitPrice;
    private Integer productPopularity;
    private String productImageUrl;
}
