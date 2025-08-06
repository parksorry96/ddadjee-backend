package com.ddadjee.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    /**
     * 활성 상태
     * - 정상적으로 사용 가능한 상태
     */
    ACTIVE("ACTIVE", "활성", 1),

    /**
     * 비활성 상태
     * - 일시적으로 사용 중지된 상태
     */
    INACTIVE("INACTIVE", "비활성", 2),

    /**
     * 대기 상태
     * - 승인 또는 처리를 기다리는 상태
     */
    PENDING("PENDING", "대기", 3),

    /**
     * 승인됨
     * - 관리자 또는 시스템에 의해 승인된 상태
     */
    APPROVED("APPROVED", "승인됨", 4),

    /**
     * 거부됨
     * - 요청이 거부된 상태
     */
    REJECTED("REJECTED", "거부됨", 5),

    /**
     * 삭제됨
     * - 논리적으로 삭제된 상태 (소프트 삭제)
     */
    DELETED("DELETED", "삭제됨", 6),

    /**
     * 만료됨
     * - 유효기간이 지난 상태
     */
    EXPIRED("EXPIRED", "만료됨", 7),

    /**
     * 일시정지
     * - 정책 위반 등으로 일시적으로 정지된 상태
     */
    SUSPENDED("SUSPENDED", "일시정지", 8),

    /**
     * 처리중
     * - 현재 작업이 진행 중인 상태
     */
    PROCESSING("PROCESSING", "처리중", 9);

    private final String code;
    private final String description;
    private final int order;

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static Status fromCode(String code) {
        if (code == null) {
            return null;
        }

        for (Status status : Status.values()) {
            if (status.code.equalsIgnoreCase(code)) {
                return status;
            }
        }

        throw new IllegalArgumentException(
                String.format("알 수 없는 상태 코드입니다: %s", code)
        );
    }

    /**
     * 활성 상태인지 확인
     */
    public boolean isActive() {
        return this == ACTIVE;
    }

    /**
     * 사용 가능한 상태인지 확인
     */
    public boolean isUsable() {
        return this == ACTIVE || this == APPROVED;
    }

    /**
     * 최종 상태인지 확인
     */
    public boolean isFinal() {
        return this == DELETED || this == EXPIRED || this == REJECTED;
    }

    /**
     * 대기 중인 상태인지 확인
     */
    public boolean isWaiting() {
        return this == PENDING || this == PROCESSING;
    }

    /**
     * 상태 전환 가능 여부 확인
     */
    public boolean canTransitionTo(Status targetStatus) {
        if (this == DELETED) {
            return false;
        }

        if (this == EXPIRED) {
            return targetStatus == DELETED;
        }

        if (this == REJECTED) {
            return targetStatus == PENDING || targetStatus == DELETED;
        }

        return switch (this) {
            case PENDING -> targetStatus == APPROVED ||
                    targetStatus == REJECTED ||
                    targetStatus == PROCESSING;
            case PROCESSING -> targetStatus == APPROVED ||
                    targetStatus == REJECTED ||
                    targetStatus == PENDING;
            case ACTIVE -> targetStatus != PENDING && targetStatus != PROCESSING;
            case INACTIVE -> targetStatus != PROCESSING;
            case APPROVED -> targetStatus != PENDING && targetStatus != PROCESSING;
            case SUSPENDED -> targetStatus == ACTIVE ||
                    targetStatus == INACTIVE ||
                    targetStatus == DELETED;
            default -> true;
        };
    }
}
