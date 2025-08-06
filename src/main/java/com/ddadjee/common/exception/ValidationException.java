package com.ddadjee.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends BaseException {

    // 필드별 검증 에러 메시지
    private final Map<String, String> fieldErrors;

    /**
     * 에러 코드만으로 검증 예외 생성
     * @param errorCode 에러 코드
     */
    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
        this.fieldErrors = new HashMap<>();
    }

    /**
     * 에러 코드와 상세 메시지로 검증 예외 생성
     * @param errorCode 에러 코드
     * @param detailMessage 상세 메시지
     */
    public ValidationException(ErrorCode errorCode, String detailMessage) {
        super(errorCode, detailMessage);
        this.fieldErrors = new HashMap<>();
    }

    /**
     * 에러 코드와 필드 에러로 검증 예외 생성
     * @param errorCode 에러 코드
     * @param fieldErrors 필드별 에러 메시지
     */
    public ValidationException(ErrorCode errorCode, Map<String, String> fieldErrors) {
        super(errorCode);
        this.fieldErrors = fieldErrors != null ? new HashMap<>(fieldErrors) : new HashMap<>();
    }

    /**
     * 에러 코드, 상세 메시지, 필드 에러로 검증 예외 생성
     * @param errorCode 에러 코드
     * @param detailMessage 상세 메시지
     * @param fieldErrors 필드별 에러 메시지
     */
    public ValidationException(ErrorCode errorCode, String detailMessage, Map<String, String> fieldErrors) {
        super(errorCode, detailMessage);
        this.fieldErrors = fieldErrors != null ? new HashMap<>(fieldErrors) : new HashMap<>();
    }

    /**
     * 기본 검증 에러 메시지로 예외 생성
     * @param message 에러 메시지
     */
    public ValidationException(String message) {
        super(ErrorCode.VALIDATION_ERROR, message);
        this.fieldErrors = new HashMap<>();
    }

    /**
     * 필드 에러 추가
     * @param field 필드명
     * @param message 에러 메시지
     */
    public void addFieldError(String field, String message) {
        this.fieldErrors.put(field, message);
    }

    /**
     * 필드 에러가 있는지 확인
     * @return 필드 에러 존재 여부
     */
    public boolean hasFieldErrors() {
        return !fieldErrors.isEmpty();
    }
}
