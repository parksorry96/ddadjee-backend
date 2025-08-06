package com.ddadjee.common.annotation;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidPassword {

    /**
     * 검증 실패 시 표시할 메시지
     */
    String message() default "{validation.password.invalid}";

    /**
     * 검증 그룹
     */
    Class<?>[] groups() default {};

    /**
     * 페이로드
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * 최소 길이
     */
    int minLength() default 8;

    /**
     * 최대 길이
     */
    int maxLength() default 100;

    /**
     * 대문자 포함 필수 여부
     */
    boolean requireUppercase() default true;

    /**
     * 소문자 포함 필수 여부
     */
    boolean requireLowercase() default true;

    /**
     * 숫자 포함 필수 여부
     */
    boolean requireDigit() default true;

    /**
     * 특수문자 포함 필수 여부
     */
    boolean requireSpecialChar() default true;

    /**
     * 허용할 특수문자 목록
     */
    String specialChars() default "!@#$%^&*()_+-=[]{}|;:,.<>?";
}
