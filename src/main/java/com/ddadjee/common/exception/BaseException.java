package com.ddadjee.common.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String detailMessage;

    /**
     * 에러 코드만으로 예외 생성
     * @param errorCode 에러 코드
     */
    protected BaseException(ErrorCode errorCode) {
        super(errorCode.getCode()); // getMessage() -> getCode()로 변경
        this.errorCode = errorCode;
        this.detailMessage = errorCode.getCode();
    }

    /**
     * 에러 코드와 상세 메시지로 예외 생성
     * @param errorCode 에러 코드
     * @param detailMessage 상세 메시지
     */
    protected BaseException(ErrorCode errorCode, String detailMessage) {
        super(detailMessage);
        this.errorCode = errorCode;
        this.detailMessage = detailMessage;
    }

    /**
     * 에러 코드와 원인 예외로 예외 생성
     * @param errorCode 에러 코드
     * @param cause 원인 예외
     */
    protected BaseException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getCode(), cause); // getMessage() -> getCode()로 변경
        this.errorCode = errorCode;
        this.detailMessage = errorCode.getCode();
    }

    /**
     * 에러 코드, 상세 메시지, 원인 예외로 예외 생성
     * @param errorCode 에러 코드
     * @param detailMessage 상세 메시지
     * @param cause 원인 예외
     */
    protected BaseException(ErrorCode errorCode, String detailMessage, Throwable cause) {
        super(detailMessage, cause);
        this.errorCode = errorCode;
        this.detailMessage = detailMessage;
    }
}