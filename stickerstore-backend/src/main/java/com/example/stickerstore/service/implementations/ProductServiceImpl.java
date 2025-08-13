package com.example.stickerstore.service.implementations;

import com.example.stickerstore.exception.ResourceNotFoundException;
import com.example.stickerstore.model.dto.request.ProductRequest;
import com.example.stickerstore.model.dto.response.ProductResponse;
import com.example.stickerstore.model.entity.Product;
import com.example.stickerstore.repository.ProductRepository;
import com.example.stickerstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;


    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = mapper.map(request, Product.class);
        return mapper.map(productRepository.save(product), ProductResponse.class);
    }

    @Override
    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> mapper.map(product, ProductResponse.class)).collect(Collectors.toList());
    }

    @Override
    public ProductResponse findProductByProductId(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product id", "product", productId)
        );
        return mapper.map(product, ProductResponse.class);
    }
}
