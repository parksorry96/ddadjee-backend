package com.ddadjee.common.constant;

public final class SecurityConstants {

    private SecurityConstants() {}

    // HTTP 헤더
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String REFRESH_TOKEN_HEADER = "X-Refresh-Token";

    // JWT 클레임
    public static final String CLAIM_USER_ID = "userId";
    public static final String CLAIM_EMAIL = "email";
    public static final String CLAIM_ROLES = "roles";

    // 권한
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_MANAGER = "MANAGER";

    // 보안 URL 패턴
    public static final String[] PUBLIC_URLS = {
            "/api/v1/auth/**",
            "/api/v1/public/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    public static final String[] ADMIN_URLS = {
            "/api/v1/admin/**"
    };
}
