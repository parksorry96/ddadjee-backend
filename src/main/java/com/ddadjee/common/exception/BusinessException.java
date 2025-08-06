package com.ddadjee.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends BaseException {
    /**
     * 에러 코드만으로 비즈니스 예외 생성
     * @param errorCode 에러 코드
     */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * 에러 코드와 상세 메시지로 비즈니스 예외 생성
     * @param errorCode 에러 코드
     * @param detailMessage 상세 메시지
     */
    public BusinessException(ErrorCode errorCode, String detailMessage) {
        super(errorCode, detailMessage);
    }

    /**
     * 에러 코드와 원인 예외로 비즈니스 예외 생성
     * @param errorCode 에러 코드
     * @param cause 원인 예외
     */
    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * 에러 코드, 상세 메시지, 원인 예외로 비즈니스 예외 생성
     * @param errorCode 에러 코드
     * @param detailMessage 상세 메시지
     * @param cause 원인 예외
     */
    public BusinessException(ErrorCode errorCode, String detailMessage, Throwable cause) {
        super(errorCode, detailMessage, cause);
    }

    /**
     * 기본 비즈니스 에러 메시지로 예외 생성
     * @param message 에러 메시지
     */
    public BusinessException(String message) {
        super(ErrorCode.INVALID_BUSINESS_STATE, message);
    }

}
