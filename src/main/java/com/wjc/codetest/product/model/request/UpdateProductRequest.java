package com.wjc.codetest.product.model.request;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record UpdateProductRequest(@Parameter(description = "수정할 INDEX 키값", example = "1") @NotNull Long id,
                                   @Parameter(description = "수정할 카테고리", example = "기아자동차1") @NotBlank String category,
                                   @Parameter(description = "수정할 이름", example = "기아") @NotBlank String name) {}

