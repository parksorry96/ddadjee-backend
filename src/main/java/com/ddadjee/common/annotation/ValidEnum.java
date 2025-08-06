package com.ddadjee.common.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumValidator.class)
public @interface ValidEnum {
    /**
     * 검증 실패 시 표시할 메시지
     */
    String message() default "{validation.enum.invalid}";

    /**
     * 검증 그룹
     */
    Class<?>[] groups() default {};

    /**
     * 페이로드
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * 검증할 Enum 클래스
     */
    Class<? extends Enum<?>> enumClass();

    /**
     * 대소문자 무시 여부
     */
    boolean ignoreCase() default false;

    /**
     * null 허용 여부
     */
    boolean nullable() default false;
}
