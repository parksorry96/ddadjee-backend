
package com.ddadjee.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 사용자 역할 열거형
 * 취준생 자격증 관리 플랫폼의 사용자 권한 정의
 */
@Getter
@RequiredArgsConstructor
public enum UserRole {

    /**
     * 일반 사용자 (무료 회원)
     * - 기본 자격증 등록/관리 (최대 10개)
     * - 기본 포트폴리오 생성
     * - 커뮤니티 읽기/쓰기
     */
    USER(
            Set.of(
                    Permission.CERTIFICATE_READ,
                    Permission.CERTIFICATE_WRITE,
                    Permission.CERTIFICATE_DELETE,
                    Permission.PORTFOLIO_READ,
                    Permission.PORTFOLIO_WRITE,
                    Permission.COMMUNITY_READ,
                    Permission.COMMUNITY_WRITE,
                    Permission.NOTIFICATION_READ
            )
    ),

    /**
     * 프리미엄 사용자 (유료 회원)
     * - 무제한 자격증 등록
     * - 고급 포트폴리오 템플릿
     * - 통계 및 분석 기능
     * - 자격증 공유 링크 생성
     */
    PREMIUM_USER(
            Set.of(
                    Permission.CERTIFICATE_READ,
                    Permission.CERTIFICATE_WRITE,
                    Permission.CERTIFICATE_DELETE,
                    Permission.CERTIFICATE_UNLIMITED,
                    Permission.PORTFOLIO_READ,
                    Permission.PORTFOLIO_WRITE,
                    Permission.PORTFOLIO_ADVANCED,
                    Permission.PORTFOLIO_SHARE,
                    Permission.COMMUNITY_READ,
                    Permission.COMMUNITY_WRITE,
                    Permission.NOTIFICATION_READ,
                    Permission.NOTIFICATION_CUSTOM,
                    Permission.ANALYTICS_READ,
                    Permission.EXPORT_DATA
            )
    ),

    /**
     * 시스템 관리자
     * - 전체 시스템 관리
     * - 사용자 계정 관리
     * - 공지사항 관리
     */
    ADMIN(
            Set.of(Permission.values())
    );

    private final Set<Permission> permissions;

    /**
     * Spring Security용 권한 목록 반환
     */
    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

    /**
     * 특정 권한 보유 여부 확인
     */
    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }

    /**
     * 프리미엄 회원 여부 확인
     */
    public boolean isPremium() {
        return this == PREMIUM_USER || this == ADMIN;
    }
}