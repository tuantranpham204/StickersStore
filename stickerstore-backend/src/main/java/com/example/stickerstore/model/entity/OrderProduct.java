package com.example.stickerstore.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder()
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderProductId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "order_id")
    private Order order;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH}
    )
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder.Default
    private Long subQuantity = 0l;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
