package com.example.stickerstore.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "products")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder()
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private String productDescription;

    private Double productUnitPrice;

    private Long productPopularity;

    private String productImageUrl;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OrderProduct> orderProducts;
}
