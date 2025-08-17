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
import org.aspectj.weaver.ast.Or;
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
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Override
    @Transactional
    public OrderProductResponse createOrderProduct(OrderProductRequest orderProductRequest, Long productId) {
        List<Order> cartList = orderRepository.findByPurchased(false);
        if (cartList.size() != 1) throw new AppException(ErrorCode.INVALID_NUMBER_OF_CART);
        Order cart = cartList.get(0);

        Optional<OrderProduct> orderProductOptional= orderProductRepository.findByOrderIdAndProductId(cart.getOrderId(), productId);

        if (orderProductOptional.isPresent()) {
            OrderProduct orderProduct = orderProductOptional.get();
            return modelMapper.map(updateOrderProductSubPriceAndUpdatedDate(cart, orderProduct, orderProductRequest), OrderProductResponse.class);
        } else {
            Product product = productRepository.findById(productId).orElseThrow(
                    () -> new ResourceNotFoundException("Product id", "product", productId)
            );
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setSubQuantity(orderProductRequest.getSubQuantity());
            orderProduct.setProduct(product);
            orderProduct.setOrder(cart);
            orderProduct.setUpdatedDate(LocalDateTime.now());
            orderProduct.setCreatedDate(LocalDateTime.now());
            orderProduct =  orderProductRepository.save(orderProduct);

            cart.setTotalQuantity(cart.getTotalQuantity() + orderProduct.getSubQuantity());
            cart.setTotalPrice(cart.getTotalPrice() + (double)orderProduct.getSubQuantity() * product.getProductUnitPrice());
            cart.setUpdatedDate(LocalDateTime.now());
            orderRepository.save(cart);
            return modelMapper.map(orderProduct, OrderProductResponse.class);
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

    @Override
    public List<OrderProductResponse> getAllCartProducts() {
        List<Order> cartList = orderRepository.findByPurchased(false);
        if (cartList.size() != 1) throw new AppException(ErrorCode.INVALID_NUMBER_OF_CART);
        Order cart = cartList.get(0);
        return orderProductRepository.findByOrderId(cart.getOrderId()).stream().map(
                orderProduct -> modelMapper.map(orderProduct, OrderProductResponse.class)
        ).collect(Collectors.toList());
    }


    private OrderProduct updateOrderProductSubPriceAndUpdatedDate(Order cart, OrderProduct orderProduct, OrderProductRequest orderProductRequest) {
        Long modifiedSubQuantity = orderProduct.getSubQuantity() + orderProductRequest.getSubQuantity();
        orderProduct.setSubQuantity(modifiedSubQuantity);
        cart.setTotalQuantity(cart.getTotalQuantity() + orderProduct.getSubQuantity());
        cart.setTotalPrice(cart.getTotalPrice() + (double)orderProduct.getSubQuantity() * orderProduct.getProduct().getProductUnitPrice());
        cart.setUpdatedDate(LocalDateTime.now());
        orderRepository.save(cart);
        return orderProductRepository.save(orderProduct);
    }

}
