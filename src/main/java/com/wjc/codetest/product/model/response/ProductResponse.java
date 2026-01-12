package com.wjc.codetest.product.model.response;

import lombok.Builder;

/**
 * <p>
 *
 * </p>
 *
 * @author : 변영우 byw1666@wjcompass.com
 * @since : 2025-10-27
 */

@Builder
public record ProductResponse(Long id , String category , String name ) {}
