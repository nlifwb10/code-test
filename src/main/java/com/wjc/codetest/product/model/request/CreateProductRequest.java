package com.wjc.codetest.product.model.request;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;


public record CreateProductRequest(@Parameter(description = "카테고리 명", example = "기아") @NotBlank String category,
                                   @Parameter(description = "이름", example = "기아자동차1") @NotBlank String name) {}
