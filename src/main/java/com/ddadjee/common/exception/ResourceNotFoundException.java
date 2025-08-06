package com.ddadjee.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends BaseException {


    // 리소스 타입 (예: User, Certificate 등)
    private final String resourceType;

    // 리소스 식별자
    private final String resourceId;

    /**
     * 에러 코드만으로 리소스 미발견 예외 생성
     * @param errorCode 에러 코드
     */
    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode);
        this.resourceType = null;
        this.resourceId = null;
    }

    /**
     * 에러 코드와 상세 메시지로 리소스 미발견 예외 생성
     * @param errorCode 에러 코드
     * @param detailMessage 상세 메시지
     */
    public ResourceNotFoundException(ErrorCode errorCode, String detailMessage) {
        super(errorCode, detailMessage);
        this.resourceType = null;
        this.resourceId = null;
    }

    /**
     * 리소스 타입과 ID로 리소스 미발견 예외 생성
     * @param resourceType 리소스 타입
     * @param resourceId 리소스 식별자
     */
    public ResourceNotFoundException(String resourceType, String resourceId) {
        super(ErrorCode.ENTITY_NOT_FOUND,
                String.format("%s를 찾을 수 없습니다. (ID: %s)", resourceType, resourceId));
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }

    /**
     * 에러 코드, 리소스 타입, ID로 리소스 미발견 예외 생성
     * @param errorCode 에러 코드
     * @param resourceType 리소스 타입
     * @param resourceId 리소스 식별자
     */
    public ResourceNotFoundException(ErrorCode errorCode, String resourceType, String resourceId) {
        super(errorCode, String.format("%s를 찾을 수 없습니다. (ID: %s)", resourceType, resourceId));
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }

    /**
     * 리소스 타입, ID, 상세 메시지로 리소스 미발견 예외 생성
     * @param resourceType 리소스 타입
     * @param resourceId 리소스 식별자
     * @param detailMessage 상세 메시지
     */
    public ResourceNotFoundException(String resourceType, String resourceId, String detailMessage) {
        super(ErrorCode.ENTITY_NOT_FOUND, detailMessage);
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }

    /**
     * 기본 리소스 미발견 메시지로 예외 생성
     * @param message 에러 메시지
     */
    public static ResourceNotFoundException withMessage(String message) {
        return new ResourceNotFoundException(ErrorCode.ENTITY_NOT_FOUND, message);
    }
}
