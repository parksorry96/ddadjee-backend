package com.ddadjee.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends BaseException {

    // 인증 시도한 사용자 식별자 (이메일, 사용자명 등)
    private final String principal;

    // 인증 실패 이유
    private final String reason;

    /**
     * 에러 코드만으로 인증 예외 생성
     * @param errorCode 에러 코드
     */
    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
        this.principal = null;
        this.reason = null;
    }

    /**
     * 에러 코드와 상세 메시지로 인증 예외 생성
     * @param errorCode 에러 코드
     * @param detailMessage 상세 메시지
     */
    public AuthenticationException(ErrorCode errorCode, String detailMessage) {
        super(errorCode, detailMessage);
        this.principal = null;
        this.reason = detailMessage;
    }

    /**
     * 에러 코드와 원인 예외로 인증 예외 생성
     * @param errorCode 에러 코드
     * @param cause 원인 예외
     */
    public AuthenticationException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
        this.principal = null;
        this.reason = null;
    }

    /**
     * 에러 코드, 사용자 식별자, 실패 이유로 인증 예외 생성
     * @param errorCode 에러 코드
     * @param principal 사용자 식별자
     * @param reason 인증 실패 이유
     */
    public AuthenticationException(ErrorCode errorCode, String principal, String reason) {
        super(errorCode, String.format("인증 실패 - 사용자: %s, 이유: %s", principal, reason));
        this.principal = principal;
        this.reason = reason;
    }

    /**
     * 기본 인증 실패 메시지로 예외 생성
     * @param message 에러 메시지
     */
    public AuthenticationException(String message) {
        super(ErrorCode.UNAUTHORIZED, message);
        this.principal = null;
        this.reason = message;
    }

    /**
     * 잘못된 자격 증명으로 인한 인증 예외 생성
     * @param principal 사용자 식별자
     * @return 인증 예외
     */
    public static AuthenticationException invalidCredentials(String principal) {
        return new AuthenticationException(
                ErrorCode.INVALID_CREDENTIALS,
                principal,
                "잘못된 자격 증명"
        );
    }

    /**
     * 만료된 토큰으로 인한 인증 예외 생성
     * @return 인증 예외
     */
    public static AuthenticationException expiredToken() {
        return new AuthenticationException(
                ErrorCode.EXPIRED_TOKEN,
                "인증 토큰이 만료되었습니다."
        );
    }

    /**
     * 유효하지 않은 토큰으로 인한 인증 예외 생성
     * @return 인증 예외
     */
    public static AuthenticationException invalidToken() {
        return new AuthenticationException(
                ErrorCode.INVALID_TOKEN,
                "유효하지 않은 인증 토큰입니다."
        );
    }

    /**
     * 계정 잠김으로 인한 인증 예외 생성
     * @param principal 사용자 식별자
     * @return 인증 예외
     */
    public static AuthenticationException accountLocked(String principal) {
        return new AuthenticationException(
                ErrorCode.ACCOUNT_LOCKED,
                principal,
                "계정이 잠겨있습니다."
        );
    }

    /**
     * 비활성화된 계정으로 인한 인증 예외 생성
     * @param principal 사용자 식별자
     * @return 인증 예외
     */
    public static AuthenticationException accountDisabled(String principal) {
        return new AuthenticationException(
                ErrorCode.ACCOUNT_DISABLED,
                principal,
                "계정이 비활성화되었습니다."
        );
    }
}
