package com.wjc.codetest.product.service;

import com.wjc.codetest._support.common.CommonMessage;
import com.wjc.codetest._support.common.CustomResponse;
import com.wjc.codetest._support.exception.CustomMessageException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductQuerydslRepository productQuerydslRepository;

    @Transactional
    public CustomResponse<ProductResponse> create(CreateProductRequest request) {
        if (productRepository.existsByCategoryAndName(request.category(), request.name())) {
            throw new CustomMessageException(CommonMessage.FAIL_DUPLICATE);
        }
        Product product = Product.createProduct(request.category(), request.name());
        productRepository.save(product);
        return getProduct(product.getId());
    }

    @Transactional
    public CustomResponse<ProductResponse> update(UpdateProductRequest request) {
        Product product = getProductById(request.id());
        // 2) (category, name) 중복 체크 (자기 자신 제외)
        if (productRepository.existsByCategoryAndNameAndIdNot(request.category(), request.name(), request.id())) {
            throw new CustomMessageException(CommonMessage.FAIL_DUPLICATE);
        }
        product.updateData(request.category(),request.name());
        return getProduct(product.getId());
    }

    @Transactional
    public CustomResponse<String> deleteById(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
        return CustomResponse.ok(CommonMessage.SUCCESS_DELETE.getMessage());
    }

    public CustomResponse<List<ProductResponse>> getListByCategory(GetProductListRequest request, Pageable pageable) {
        Page<ProductResponse> productListResponse = productQuerydslRepository.findAllByPageable(request,pageable);
        return CustomResponse.pageResponse(productListResponse);
    }

    public CustomResponse<List<String>> getUniqueCategories() {
        List<String> uniqueCategories = productRepository.findDistinctCategories();
        return CustomResponse.ok(uniqueCategories);
    }

    public CustomResponse<ProductResponse> getProduct(Long id) {
        Product product = getProductById(id);
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .category(product.getCategory())
                .name(product.getName())
                .build();
        return CustomResponse.ok(productResponse);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new CustomMessageException(CommonMessage.FAIL_NO_RESULT));
    }
}