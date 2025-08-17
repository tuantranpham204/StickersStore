package com.example.stickerstore.repository;

import com.example.stickerstore.model.entity.OrderProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {
    @Query("FROM OrderProduct op WHERE op.order.orderId = :orderId")
    List<OrderProduct> findByOrderId(Long orderId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE OrderProduct op SET op.subQuantity=:modifiedSubQuantity, op.updatedDate=:updatedDate WHERE op.orderProductId=:orderProductId")
    int updateSubQuantityAndUpdatedDate(@Param("orderProductId") Long orderProductId, @Param("modifiedSubQuantity") Long modifiedSubQuantity, @Param("updatedDate") LocalDateTime updatedDate);

    @Query("FROM OrderProduct op WHERE op.order.orderId=:orderId AND op.product.productId=:productId ")
    Optional<OrderProduct> findByOrderIdAndProductId(Long orderId, Long productId);

    @Query("FROM OrderProduct op WHERE op.order.orderId=:orderId AND op.orderProductId=:orderProductId")
    Optional<OrderProduct> findByOrderIdAndOrderProductId(Long orderId, Long orderProductId);
}
