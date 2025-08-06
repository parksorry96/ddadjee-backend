package com.ddadjee.common.constant;

public final class PaginationConstants {

    private PaginationConstants() {
        // 인스턴스화 방지
    }

    // 페이지 관련
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int MIN_PAGE_NUMBER = 0;

    // 사이즈 관련
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MIN_PAGE_SIZE = 1;
    public static final int MAX_PAGE_SIZE = 100;

    // 정렬 관련
    public static final String DEFAULT_SORT_BY = "createdAt";
    public static final String DEFAULT_SORT_DIRECTION = "DESC";
    public static final String SORT_ASC = "ASC";
    public static final String SORT_DESC = "DESC";
    public static final String SORT_DIRECTION_PATTERN = "^(ASC|DESC)$";
}
