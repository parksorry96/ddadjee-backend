package com.ddadjee.common.dto;

import java.util.List;

/**
 * 페이지네이션 응답 유틸리티
 */
public final class PageResponseUtils {

    private PageResponseUtils() {}

    public static <T> PageResponse<T> emptyPage() {
        return new PageResponse<>(
                List.of(),
                0,
                20,
                0L,
                0,
                true,
                true,
                true,
                0
        );
    }

    public static <T> SliceResponse<T> emptySlice() {
        return new SliceResponse<>(
                List.of(),
                0,
                20,
                false,
                false,
                true,
                true,
                true,
                0
        );
    }
}
