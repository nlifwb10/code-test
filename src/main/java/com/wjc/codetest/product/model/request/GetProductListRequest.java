package com.wjc.codetest.product.model.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductListRequest {

    @Parameter(description = "카테고리 명", example = "기아")
    private String category;
}