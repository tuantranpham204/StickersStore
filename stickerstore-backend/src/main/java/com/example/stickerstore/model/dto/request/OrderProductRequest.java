package com.example.stickerstore.model.dto.request;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder()
public class OrderProductRequest {
    private Long subQuantity;
}
