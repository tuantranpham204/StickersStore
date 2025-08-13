package com.example.stickerstore.repository;

import com.example.stickerstore.model.entity.Order;
import com.example.stickerstore.model.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("FROM Order o WHERE o.purchased=:purchased")
    List<Order> findByPurchased(boolean purchased);
}
