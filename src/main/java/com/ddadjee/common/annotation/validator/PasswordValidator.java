package com.ddadjee.common.annotation.validator;

import com.ddadjee.common.annotation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private int minLength;
    private int maxLength;
    private boolean requireUppercase;
    private boolean requireLowercase;
    private boolean requireDigit;
    private boolean requireSpecialChar;
    private String specialChars;

    @Override
    public void initialize(ValidPassword annotation) {
        this.minLength = annotation.minLength();
        this.maxLength = annotation.maxLength();
        this.requireUppercase = annotation.requireUppercase();
        this.requireLowercase = annotation.requireLowercase();
        this.requireDigit = annotation.requireDigit();
        this.requireSpecialChar = annotation.requireSpecialChar();
        this.specialChars = annotation.specialChars();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // null 체크
        if (password == null) {
            return false;
        }

        // 검증 실패 메시지를 저장할 리스트
        List<String> violations = new ArrayList<>();

        // 길이 검증
        if (password.length() < minLength) {
            violations.add(String.format("최소 %d자 이상", minLength));
        }
        if (password.length() > maxLength) {
            violations.add(String.format("최대 %d자 이하", maxLength));
        }

        // 대문자 검증
        if (requireUppercase && !Pattern.compile("[A-Z]").matcher(password).find()) {
            violations.add("대문자 포함");
        }

        // 소문자 검증
        if (requireLowercase && !Pattern.compile("[a-z]").matcher(password).find()) {
            violations.add("소문자 포함");
        }

        // 숫자 검증
        if (requireDigit && !Pattern.compile("[0-9]").matcher(password).find()) {
            violations.add("숫자 포함");
        }

        // 특수문자 검증
        if (requireSpecialChar) {
            String regex = "[" + Pattern.quote(specialChars) + "]";
            if (!Pattern.compile(regex).matcher(password).find()) {
                violations.add("특수문자 포함");
            }
        }

        // 검증 실패 시 커스텀 메시지 설정
        if (!violations.isEmpty()) {
            context.disableDefaultConstraintViolation();
            String message = "비밀번호는 다음 조건을 만족해야 합니다: " + String.join(", ", violations);
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
