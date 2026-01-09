package com.wjc.codetest.product.service;

import com.wjc.codetest._support.common.CustomResponse;
import com.wjc.codetest.product.model.request.CreateProductRequest;
import com.wjc.codetest.product.model.request.GetProductListRequest;
import com.wjc.codetest.product.model.domain.Product;
import com.wjc.codetest.product.model.request.UpdateProductRequest;
import com.wjc.codetest.product.model.response.ProductResponse;
import com.wjc.codetest.product.repository.ProductRepository;
import com.wjc.codetest.product.repository.querydsl.ProductQuerydslRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductQuerydslRepository productQuerydslRepository;

    public Product create(CreateProductRequest dto) {
        Product product = new Product(dto.getCategory(), dto.getName());
        return productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            throw new RuntimeException("product not found");
        }
        return productOptional.get();
    }

    public Product update(UpdateProductRequest dto) {
        Product product = getProductById(dto.getId());
//        product.setCategory(dto.getCategory());
//        product.setName(dto.getName());
        Product updatedProduct = productRepository.save(product);
        return updatedProduct;

    }

    public void deleteById(Long productId) {
        Product product = getProductById(productId);
        productRepository.delete(product);
    }

    public CustomResponse<List<ProductResponse>> getListByCategory(GetProductListRequest request, Pageable pageable) {
        Page<ProductResponse> productListResponse = productQuerydslRepository.findAllByPageable(request,pageable);
        return CustomResponse.pageResponse(productListResponse);
    }

    public List<String> getUniqueCategories() {
        return productRepository.findDistinctCategories();
    }
}