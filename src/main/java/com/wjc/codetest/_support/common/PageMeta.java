package com.wjc.codetest._support.common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PageMeta(
        long totalCount,
        int nowPage,
        int pageSize,
        int totalPage
) {
    public static PageMeta of(long totalCount, int nowPage, int pageSize) {
        int totalPage = (int) Math.ceil((double) totalCount / pageSize);
        return new PageMeta(totalCount, nowPage, pageSize, totalPage);
    }
}