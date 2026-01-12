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
        String errMessage,
        @Schema(title = "데이터")
        T data,
        @Schema(title = "페이지 Meta 데이터" )
        PageMeta page //
) {

    /**
     * 리스트 공통함수 ( 페이징 처리 X 일 경우 )
     * @param data 조회 리스트 객체
     * @return 조회 데이터 값과 공통 응답객체
     */
    public static <T> CustomResponse<T> ok(T data) {
        return new CustomResponse<>(true, HttpStatus.OK.value(), null, data, null);
    }

    /**
     * 페이징 리스트 공통 함수
     * @param page<T> 페이징 조회 결과 객체
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

    /**
     * 실패 에러 공통 함수
     * @param httpStatus HTTP 상태 코드 (예: 400, 404, 500 등)
     * @param errMessage 클라이언트에게 전달할 에러 메시지
     * @return 실패 상태를 나타내는 공통 응답 객체
     */
    public static <T> CustomResponse<T> failed(HttpStatus httpStatus, String errMessage) {
        return new CustomResponse<>(false,httpStatus.value(),errMessage,null,null);
    }
}
