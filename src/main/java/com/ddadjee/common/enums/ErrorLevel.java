package com.ddadjee.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorLevel {
    /**
     * 치명적 오류 - 시스템 중단이 필요한 수준
     * 예: 데이터베이스 연결 실패, 필수 서비스 중단
     */
    CRITICAL(1, "CRITICAL", "치명적"),

    /**
     * 일반 오류 - 기능 실행 실패
     * 예: 비즈니스 로직 오류, 외부 API 호출 실패
     */
    ERROR(2, "ERROR", "오류"),

    /**
     * 경고 - 잠재적 문제 상황
     * 예: 권장하지 않는 API 사용, 성능 저하 감지
     */
    WARNING(3, "WARNING", "경고"),

    /**
     * 정보 - 일반적인 정보성 메시지
     * 예: 사용자 로그인, 데이터 처리 완료
     */
    INFO(4, "INFO", "정보"),

    /**
     * 디버그 - 개발/디버깅용 상세 정보
     * 예: 메소드 진입/종료, 변수 값 추적
     */
    DEBUG(5, "DEBUG", "디버그");

    private final int priority;      // 우선순위 (낮을수록 심각)
    private final String code;        // 영문 코드
    private final String description; // 한글 설명

    /**
     * 현재 레벨이 지정된 레벨보다 심각한지 확인
     */
    public boolean isMoreSevereThan(ErrorLevel other) {
        return this.priority < other.priority;
    }

    /**
     * 현재 레벨이 지정된 레벨 이상으로 심각한지 확인
     */
    public boolean isAtLeast(ErrorLevel other) {
        return this.priority <= other.priority;
    }

    /**
     * 코드 문자열로 ErrorLevel 찾기
     */
    public static ErrorLevel fromCode(String code) {
        if (code == null || code.isEmpty()) {
            return INFO;
        }

        for (ErrorLevel level : values()) {
            if (level.code.equalsIgnoreCase(code)) {
                return level;
            }
        }
        return INFO; // 기본값
    }

    /**
     * 현재 레벨이 에러 수준 이상인지 확인
     */
    public boolean isError() {
        return this == CRITICAL || this == ERROR;
    }

    /**
     * HTTP 상태 코드 매핑 제안
     */
    public int suggestedHttpStatus() {
        return switch (this) {
            case CRITICAL -> 503; // Service Unavailable
            case ERROR -> 500;    // Internal Server Error
            case WARNING -> 400;  // Bad Request
            case INFO -> 200;     // OK
            case DEBUG -> 200;    // OK
        };
    }

    /**
     * 로그 레벨에 따른 색상 코드 반환 (ANSI 색상)
     */
    public String getColorCode() {
        return switch (this) {
            case CRITICAL -> "\u001B[35m"; // 보라색
            case ERROR -> "\u001B[31m";    // 빨간색
            case WARNING -> "\u001B[33m";  // 노란색
            case INFO -> "\u001B[32m";     // 초록색
            case DEBUG -> "\u001B[36m";    // 청록색
        };
    }
}
