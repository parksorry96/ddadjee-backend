package com.ddadjee.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageUtils {

        private final MessageSource messageSource;

    /**
     * 현재 로케일로 메시지 조회
     */
    public String getMessage(String code) {
        return getMessage(code, (Object[]) null);
    }

    /**
     * 현재 로케일로 메시지 조회 (파라미터 포함)
     */
    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    /**
     * 특정 로케일로 메시지 조회
     */
    public String getMessage(String code, Object[] args, Locale locale) {
        return messageSource.getMessage(code, args, locale);
    }

    /**
     * 기본값과 함께 메시지 조회
     */
    public String getMessage(String code, String defaultMessage) {
        return messageSource.getMessage(code, null, defaultMessage, LocaleContextHolder.getLocale());
    }
}
