package com.example.stickerstore.service.implementations;

import com.example.stickerstore.exception.AppException;
import com.example.stickerstore.exception.ErrorCode;
import com.example.stickerstore.exception.ResourceNotFoundException;
import com.example.stickerstore.model.dto.response.OrderResponse;
import com.example.stickerstore.model.entity.Order;
import com.example.stickerstore.repository.OrderRepository;
import com.example.stickerstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrderResponse createEmptyCart() {
        Order cart = new Order();
        cart.setCreatedDate(LocalDateTime.now());
        cart.setUpdatedDate(LocalDateTime.now());
        return modelMapper.map(orderRepository.save(cart), OrderResponse.class);
    }

    @Override
    public OrderResponse purchaseOrder() {
        List<Order> cartList = orderRepository.findByPurchased(false);
        if (cartList.size() != 1) throw new AppException(ErrorCode.INVALID_NUMBER_OF_CART);
        Order cart = cartList.get(0);
        cart.setPurchased(true);
        cart.setUpdatedDate(LocalDateTime.now());
        cart.setPurchasedDate(LocalDateTime.now());
        Order emptyCart = new Order();
        orderRepository.save(emptyCart);
        return modelMapper.map(orderRepository.save(cart), OrderResponse.class);
    }

    @Override
    public OrderResponse getCart() {
        List<Order> cartList = orderRepository.findByPurchased(false);
        if (cartList.size() != 1) throw new AppException(ErrorCode.INVALID_NUMBER_OF_CART);
        return modelMapper.map(cartList.get(0), OrderResponse.class);
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("order id", "order", orderId)
        );
        return  modelMapper.map(order, OrderResponse.class);
    }
}
