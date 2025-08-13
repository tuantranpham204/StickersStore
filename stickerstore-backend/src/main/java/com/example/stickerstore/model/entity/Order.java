package com.example.stickerstore.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder()
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OrderProduct> orderProducts =  new HashSet<>();

    @Builder.Default
    private Double totalPrice = 0.0;

    @Builder.Default
    private Long totalQuantity = 0l;
    @Builder.Default
    private boolean purchased = false;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private LocalDateTime purchasedDate;
}
