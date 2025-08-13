package com.example.stickerstore.repository;

import com.example.stickerstore.model.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {
    @Query("FROM OrderProduct op WHERE op.order.orderId = :orderId")
    List<OrderProduct> findByOrderId(Long orderId);

    @Modifying
    @Query(value = "INSERT INTO order_products (created_date, order_id, product_id, sub_quantity, updated_date) " +
            "VALUES (:createdDate, :orderId, :productId, :subQuantity, :updatedDate) " +
            "ON DUPLICATE KEY UPDATE " +
            "sub_quantity = sub_quantity + VALUES(sub_quantity), " +
            "updated_date = VALUES(updated_date)",
            nativeQuery = true)
    void upsertOrderProduct(@Param("createdDate") LocalDateTime createdDate,
                            @Param("orderId") Long orderId,
                            @Param("productId") Long productId,
                            @Param("subQuantity") Long subQuantity,
                            @Param("updatedDate") LocalDateTime updatedDate);

}
