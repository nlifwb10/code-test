package com.wjc.codetest.product.model.request;

import io.swagger.v3.oas.annotations.Parameter;


public record GetProductListRequest(@Parameter(description = "카테고리 명", example = "기아") String category ) {}