package com.wjc.codetest._support.common;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;


@Slf4j
@Schema(title = "공통 Response")
public record CustomResponse<T>(

        @Schema(title = "성공여부", example = "true / false")
        boolean success,
        @Schema(title = "응답코드", example = "200")
        Integer code,
        @Schema(title = "에러메시지",example = "에러 내용")
        String message,
        @Schema(title = "데이터")
        T data,
        @Schema(title = "페이지 Meta 데이터" )
        PageMeta page //
) {

    public static <T> CustomResponse<T> ok(T data) {
        return new CustomResponse<>(true, HttpStatus.OK.value(), null, data, null);
    }

    /**
     * 페이징 리스트 공통 함수
     * @param page 페이징 조회 결과 객체
     * @param pageableQuery 현재 페이지 번호 및 페이지 사이즈 정보를 담고 있는 조회 조건 객체
     * @param <T> 페이징 대상 데이터의 타입
     * @return 페이징 데이터 목록과 페이지 메타 정보를 포함한 공통 응답 객체
     */
    public static <T> CustomResponse<List<T>> pageResponse(Page<T> page) {
        return new CustomResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                page.getContent(),
                PageMeta.of(page.getTotalElements(), page.getNumber(), page.getSize())
        );
    }
}
