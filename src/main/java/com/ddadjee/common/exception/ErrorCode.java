package com.ddadjee.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 공통 에러 (COMMON)
    // 공통 에러 (COMMON)
    INTERNAL_SERVER_ERROR("error.common.internal_server", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_INPUT_VALUE("error.common.invalid_input", HttpStatus.BAD_REQUEST),
    METHOD_NOT_ALLOWED("error.common.method_not_allowed", HttpStatus.METHOD_NOT_ALLOWED),
    ENTITY_NOT_FOUND("error.common.entity_not_found", HttpStatus.NOT_FOUND),
    INVALID_TYPE_VALUE("error.common.invalid_type", HttpStatus.BAD_REQUEST),
    HANDLE_ACCESS_DENIED("error.common.access_denied", HttpStatus.FORBIDDEN),

    // 인증 관련 에러 (AUTH)
    UNAUTHORIZED("error.auth.unauthorized", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN("error.auth.invalid_token", HttpStatus.UNAUTHORIZED),
    EXPIRED_TOKEN("error.auth.expired_token", HttpStatus.UNAUTHORIZED),
    INVALID_CREDENTIALS("error.auth.invalid_credentials", HttpStatus.UNAUTHORIZED),
    ACCOUNT_LOCKED("error.auth.account_locked", HttpStatus.FORBIDDEN),
    ACCOUNT_DISABLED("error.auth.account_disabled", HttpStatus.FORBIDDEN),

    // 사용자 관련 에러 (USER)
    USER_NOT_FOUND("error.user.not_found", HttpStatus.NOT_FOUND),
    DUPLICATE_EMAIL("error.user.duplicate_email", HttpStatus.CONFLICT),
    DUPLICATE_USERNAME("error.user.duplicate_username", HttpStatus.CONFLICT),
    INVALID_PASSWORD("error.user.invalid_password", HttpStatus.BAD_REQUEST),
    PASSWORD_MISMATCH("error.user.password_mismatch", HttpStatus.BAD_REQUEST),

    // 자격증 관련 에러 (CERTIFICATE)
    CERTIFICATE_NOT_FOUND("error.cert.not_found", HttpStatus.NOT_FOUND),
    DUPLICATE_CERTIFICATE("error.cert.duplicate", HttpStatus.CONFLICT),
    INVALID_CERTIFICATE_DATE("error.cert.invalid_date", HttpStatus.BAD_REQUEST),
    CERTIFICATE_EXPIRED("error.cert.expired", HttpStatus.BAD_REQUEST),
    CERTIFICATE_LIMIT_EXCEEDED("error.cert.limit_exceeded", HttpStatus.BAD_REQUEST),

    // 비즈니스 로직 에러 (BUSINESS)
    INVALID_BUSINESS_STATE("error.business.invalid_state", HttpStatus.BAD_REQUEST),
    BUSINESS_RULE_VIOLATION("error.business.rule_violation", HttpStatus.BAD_REQUEST),
    OPERATION_NOT_PERMITTED("error.business.operation_not_permitted", HttpStatus.FORBIDDEN),
    RESOURCE_ALREADY_EXISTS("error.business.resource_exists", HttpStatus.CONFLICT),
    INSUFFICIENT_PERMISSION("error.business.insufficient_permission", HttpStatus.FORBIDDEN),

    // 검증 에러 (VALIDATION)
    VALIDATION_ERROR("error.validation.error", HttpStatus.BAD_REQUEST),
    REQUIRED_FIELD_MISSING("error.validation.required_field", HttpStatus.BAD_REQUEST),
    INVALID_FORMAT("error.validation.invalid_format", HttpStatus.BAD_REQUEST),
    OUT_OF_RANGE("error.validation.out_of_range", HttpStatus.BAD_REQUEST),
    CONSTRAINT_VIOLATION("error.validation.constraint_violation", HttpStatus.BAD_REQUEST);

    private final String code;  // MessageSource에서 사용할 메시지 코드
    private final HttpStatus httpStatus;  // HTTP 응답 상태 코드
}
