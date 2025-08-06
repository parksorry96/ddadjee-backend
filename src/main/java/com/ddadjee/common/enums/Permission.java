package com.ddadjee.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    // 자격증 관련 권한
    CERTIFICATE_READ("certificate:read", "자격증 조회"),
    CERTIFICATE_WRITE("certificate:write", "자격증 등록/수정"),
    CERTIFICATE_DELETE("certificate:delete", "자격증 삭제"),
    CERTIFICATE_UNLIMITED("certificate:unlimited", "무제한 자격증 등록"),

    // 포트폴리오 관련 권한
    PORTFOLIO_READ("portfolio:read", "포트폴리오 조회"),
    PORTFOLIO_WRITE("portfolio:write", "포트폴리오 생성/수정"),
    PORTFOLIO_ADVANCED("portfolio:advanced", "고급 포트폴리오 템플릿"),
    PORTFOLIO_SHARE("portfolio:share", "포트폴리오 공유 링크 생성"),

    // 커뮤니티 관련 권한
    COMMUNITY_READ("community:read", "커뮤니티 읽기"),
    COMMUNITY_WRITE("community:write", "커뮤니티 글쓰기"),

    // 알림 관련 권한
    NOTIFICATION_READ("notification:read", "알림 조회"),
    NOTIFICATION_CUSTOM("notification:custom", "알림 커스터마이징"),

    // 분석 및 통계 권한
    ANALYTICS_READ("analytics:read", "통계 조회"),

    // 기타 권한
    EXPORT_DATA("export:data", "데이터 내보내기"),
    SYSTEM_CONFIG("system:config", "시스템 설정"),
    ANNOUNCEMENT_MANAGE("announcement:manage", "공지사항 관리");

    private final String permission;
    private final String description;
}

