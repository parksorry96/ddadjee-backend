package com.ddadjee.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        boolean success,
        String message,
        T data,
        LocalDateTime timestamp
) {
    /**
     * 성공 응답 생성 (데이터만)
     * 메시지는 컨트롤러에서 MessageSource를 통해 설정
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
                true,
                null,  // 컨트롤러에서 설정
                data,
                LocalDateTime.now()
        );
    }

    /**
     * 성공 응답 생성 (메시지와 데이터)
     * 메시지는 이미 MessageSource에서 가져온 값
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(
                true,
                message,
                data,
                LocalDateTime.now()
        );
    }

    /**
     * 성공 응답 생성 (메시지만)
     */
    public static ApiResponse<Void> success(String message) {
        return new ApiResponse<>(
                true,
                message,
                null,
                LocalDateTime.now()
        );
    }
}
