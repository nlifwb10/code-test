package com.wjc.codetest.product.repository.querydsl;


import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.wjc.codetest._support.config.QuerydslRepositorySupport;
import com.wjc.codetest.product.model.domain.Product;
import com.wjc.codetest.product.model.request.GetProductListRequest;
import com.wjc.codetest.product.model.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import static com.wjc.codetest.product.model.domain.QProduct.product;

@Repository
public class ProductQuerydslRepository extends QuerydslRepositorySupport {


    public ProductQuerydslRepository() {
        super(Product.class); // 엔티티 지정
    }

    public Page<ProductResponse> findAllByPageable(GetProductListRequest request, Pageable pageable) {

        // 내용
        JPAQuery<ProductResponse> contentQuery = getQueryFactory()
                .select(Projections.constructor(
                        ProductResponse.class,
                        product.id,
                        product.category,
                        product.name
                ))
                .from(product)
                .where(containsCategoryName(request.category()));

        // 전체 갯수
        JPAQuery<Long> countQuery = getQueryFactory()
                .select(product.id.count())
                .from(product)
                .where(containsCategoryName(request.category()));

        return PageableExecutionUtils.getPage(getQuerydsl().applyPagination(pageable, contentQuery).fetch(),pageable,countQuery::fetchOne);
    }

    private BooleanExpression containsCategoryName(String categoryName) {
        return StringUtils.hasText(categoryName) ? product.category.contains(categoryName) : null;
    }
}
