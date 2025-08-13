package com.example.stickerstore.service.implementations;

import com.example.stickerstore.exception.AppException;
import com.example.stickerstore.exception.ErrorCode;
import com.example.stickerstore.exception.ResourceNotFoundException;
import com.example.stickerstore.model.dto.request.OrderProductRequest;
import com.example.stickerstore.model.dto.response.OrderProductResponse;
import com.example.stickerstore.model.dto.response.OrderResponse;
import com.example.stickerstore.model.entity.Order;
import com.example.stickerstore.model.entity.OrderProduct;
import com.example.stickerstore.model.entity.Product;
import com.example.stickerstore.repository.OrderProductRepository;
import com.example.stickerstore.repository.OrderRepository;
import com.example.stickerstore.repository.ProductRepository;
import com.example.stickerstore.service.OrderProductService;
import com.example.stickerstore.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final OrderService orderService;
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderProductResponse createOrderProduct(OrderProductRequest orderProductRequest, Long productId) {
        Order cart = modelMapper.map(orderService.getCart(), Order.class);

        // Check if product exists for response mapping
        OrderProduct existingProduct = null;
        for (OrderProduct orderProduct : cart.getOrderProducts()) {
            if (orderProduct.getProduct().getProductId().equals(productId)) {
                existingProduct = orderProduct;
                break;
            }
        }

        if (existingProduct != null) {
            // Update existing - use the native upsert
            orderProductRepository.upsertOrderProduct(
                    LocalDateTime.now(),
                    cart.getOrderId(),
                    productId,
                    orderProductRequest.getSubQuantity(),
                    LocalDateTime.now()
            );

            // Update cart totals
            cart.setTotalQuantity(cart.getTotalQuantity() + orderProductRequest.getSubQuantity());
            cart.setTotalPrice(cart.getTotalPrice() + (double)orderProductRequest.getSubQuantity() * existingProduct.getProduct().getProductUnitPrice());
            orderRepository.save(cart);

            // Update the existing object for response
            existingProduct.setSubQuantity(existingProduct.getSubQuantity() + orderProductRequest.getSubQuantity());
            existingProduct.setUpdatedDate(LocalDateTime.now());

            return modelMapper.map(existingProduct, OrderProductResponse.class);
        } else {
            // Create new product
            Product product = productRepository.findById(productId).orElseThrow(
                    () -> new ResourceNotFoundException("Product id", "product", productId)
            );

            // Use native upsert (will insert since it doesn't exist)
            orderProductRepository.upsertOrderProduct(
                    LocalDateTime.now(),
                    cart.getOrderId(),
                    productId,
                    orderProductRequest.getSubQuantity(),
                    LocalDateTime.now()
            );

            // Update cart totals
            cart.setTotalQuantity(cart.getTotalQuantity() + orderProductRequest.getSubQuantity());
            cart.setTotalPrice(cart.getTotalPrice() + (double)orderProductRequest.getSubQuantity() * product.getProductUnitPrice());
            orderRepository.save(cart);

            // Create response object
            OrderProduct newOrderProduct = new OrderProduct();
            newOrderProduct.setProduct(product);
            newOrderProduct.setSubQuantity(orderProductRequest.getSubQuantity());
            newOrderProduct.setCreatedDate(LocalDateTime.now());
            newOrderProduct.setUpdatedDate(LocalDateTime.now());

            return modelMapper.map(newOrderProduct, OrderProductResponse.class);
        }
    }

    @Override
    public String deleteOrderProductById(Long orderProductId) {
        OrderProduct orderProduct = orderProductRepository.findById(orderProductId).orElseThrow(
                () -> new ResourceNotFoundException("Order product id", "order product", orderProductId)
        );
        Order order = orderProduct.getOrder();
        if (order.isPurchased()) throw new AppException(ErrorCode.CANNOT_CHANGE_PURCHASED_ORDERS);
        orderProductRepository.deleteById(orderProductId);
        return String.format("Order product with id %s has been deleted", orderProductId);
    }

    @Override
    public List<OrderProductResponse> getAllOrderProductsByOrderId(Long orderId) {
        return orderProductRepository.findByOrderId(orderId).stream().map(
          orderProduct -> modelMapper.map(orderProduct, OrderProductResponse.class)
        ).collect(Collectors.toList());
    }
}
