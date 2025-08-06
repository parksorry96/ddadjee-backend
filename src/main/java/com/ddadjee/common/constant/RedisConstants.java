package com.ddadjee.common.constant;

public final class RedisConstants {

    private RedisConstants() {}

    // 캐시 키 접두사
    public static final String KEY_PREFIX = "ddadjee:";
    public static final String USER_KEY_PREFIX = KEY_PREFIX + "user:";
    public static final String CERTIFICATE_KEY_PREFIX = KEY_PREFIX + "cert:";
    public static final String SESSION_KEY_PREFIX = KEY_PREFIX + "session:";
    public static final String VERIFICATION_KEY_PREFIX = KEY_PREFIX + "verify:";

    // TTL (초 단위)
    public static final long DEFAULT_TTL = 3600;           // 1시간
    public static final long USER_CACHE_TTL = 1800;        // 30분
    public static final long CERTIFICATE_CACHE_TTL = 7200; // 2시간
    public static final long SESSION_TTL = 86400;          // 24시간
    public static final long VERIFICATION_CODE_TTL = 300;  // 5분
}
