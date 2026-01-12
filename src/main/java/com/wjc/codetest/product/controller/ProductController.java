package com.wjc.codetest.product.controller;

import com.wjc.codetest._support.common.CustomResponse;
import com.wjc.codetest.product.model.request.CreateProductRequest;
import com.wjc.codetest.product.model.request.GetProductListRequest;
import com.wjc.codetest.product.model.request.UpdateProductRequest;
import com.wjc.codetest.product.model.response.ProductResponse;
import com.wjc.codetest.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Tag(name = "Product API", description = "제품관련 API")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "제품상세 조회", description = "제품 ID로 해당 제품을 조회한다.")
    @GetMapping(value = "/{id}")
    public CustomResponse<ProductResponse> getProductById(@PathVariable(name = "id") Long id){
        return productService.getProduct(id);
    }
    
    @Operation(summary = "제품 등록", description = "새로운 제품을 등록한다.")
    @PostMapping
    public CustomResponse<ProductResponse> createProduct(@Valid  @RequestBody CreateProductRequest request){
        return productService.create(request);
    }

    @Operation(summary = "제품 삭제", description = "해당 제품을 삭제한다.")
    @DeleteMapping(value = "/{productId}")
    public CustomResponse<String> deleteProduct(@PathVariable(name = "productId") Long productId){
        return productService.deleteById(productId);
    }

    @Operation(summary = "제품 수정", description = "해당 제품을 수정한다.")
    @PutMapping
    public CustomResponse<ProductResponse> updateProduct(@Valid @RequestBody UpdateProductRequest request){
        return productService.update(request);
    }

    @Operation(summary = "제품 목록조회", description = "제품목록을 조회한다.")
    @GetMapping
    public CustomResponse<List<ProductResponse>> getProductListByCategory(@ParameterObject GetProductListRequest request, @ParameterObject Pageable pageable){
         return productService.getListByCategory(request,pageable);
    }

    @Operation(summary = "제품별 category 목록조회", description = "제품별 category 를 조회한다.")
    @GetMapping(value = "/category/list")
    public CustomResponse<List<String>> getProductListByCategory(){
        return productService.getUniqueCategories();
    }
}