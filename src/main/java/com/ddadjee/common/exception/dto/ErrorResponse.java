package com.ddadjee.common.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * API 에러 응답을 위한 표준 Record
 *
 * @param timestamp   에러 발생 시간
 * @param status      HTTP 상태 코드
 * @param error       에러 코드
 * @param message     에러 메시지
 * @param path        요청 경로
 * @param fieldErrors 필드 검증 에러 목록
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<FieldError> fieldErrors
) {

    /**
     * 필드 검증 에러 정보를 담는 Record
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record FieldError(
            String field,
            Object value,
            String reason
    ) {
        /**
         * Spring의 FieldError를 변환
         */
        public static FieldError of(org.springframework.validation.FieldError error) {
            return new FieldError(
                    error.getField(),
                    error.getRejectedValue(),
                    error.getDefaultMessage()
            );
        }
    }

    /**
     * 기본 에러 응답 생성
     */
    public static ErrorResponse of(int status, String error, String message, String path) {
        return new ErrorResponse(
                LocalDateTime.now(),
                status,
                error,
                message,
                path,
                null
        );
    }

    /**
     * 필드 에러를 포함한 응답 생성 (GlobalExceptionHandler에서 사용)
     */
    public static ErrorResponse withFieldErrors(
            int status,
            String error,
            String message,
            String path,
            List<FieldError> fieldErrors
    ) {
        return new ErrorResponse(
                LocalDateTime.now(),
                status,
                error,
                message,
                path,
                fieldErrors
        );
    }

    /**
     * BindingResult를 포함한 검증 에러 응답 생성
     */
    public static ErrorResponse of(
            int status,
            String error,
            String message,
            String path,
            org.springframework.validation.BindingResult bindingResult
    ) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors()
                .stream()
                .map(FieldError::of)
                .collect(Collectors.toList());

        return new ErrorResponse(
                LocalDateTime.now(),
                status,
                error,
                message,
                path,
                fieldErrors
        );
    }
}
