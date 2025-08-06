package com.ddadjee.common.annotation.validator;

import com.ddadjee.common.annotation.ValidEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumValidator implements ConstraintValidator<ValidEnum, String> {
    private Set<String> acceptedValues;
    private boolean ignoreCase;
    private boolean nullable;

    @Override
    public void initialize(ValidEnum annotation) {
        // Enum 클래스의 모든 값을 Set으로 저장
        acceptedValues = Arrays.stream(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());

        ignoreCase = annotation.ignoreCase();
        nullable = annotation.nullable();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // null 값 처리
        if (value == null) {
            return nullable;
        }

        // 빈 문자열 처리
        if (value.trim().isEmpty()) {
            return false;
        }

        // 대소문자 무시 옵션 처리
        if (ignoreCase) {
            return acceptedValues.stream()
                    .anyMatch(enumValue -> enumValue.equalsIgnoreCase(value));
        }

        // 정확한 매칭
        return acceptedValues.contains(value);
    }
}
