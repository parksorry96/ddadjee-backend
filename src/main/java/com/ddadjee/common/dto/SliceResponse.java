package com.ddadjee.common.dto;

import com.ddadjee.common.constant.PaginationConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Slice;

import java.util.List;

/**
 * 무한 스크롤을 위한 Slice 응답 DTO
 * 전체 개수를 조회하지 않아 성능상 이점이 있음
 *
 * @param <T> 콘텐츠 타입
 * @param content 현재 슬라이스의 콘텐츠 목록
 * @param pageNumber 현재 페이지 번호 (0부터 시작)
 * @param pageSize 페이지 크기
 * @param hasNext 다음 페이지 존재 여부
 * @param hasPrevious 이전 페이지 존재 여부
 * @param first 첫 페이지 여부
 * @param last 마지막 페이지 여부
 * @param empty 빈 슬라이스 여부
 * @param numberOfElements 현재 슬라이스의 요소 개수
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record SliceResponse<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        boolean hasNext,
        boolean hasPrevious,
        boolean first,
        boolean last,
        boolean empty,
        int numberOfElements
) {
    /**
     * Spring Data Slice 객체로부터 SliceResponse 생성
     */
    public static <T> SliceResponse<T> from(Slice<T> slice) {
        return new SliceResponse<>(
                slice.getContent(),
                slice.getNumber(),
                slice.getSize(),
                slice.hasNext(),
                slice.hasPrevious(),
                slice.isFirst(),
                slice.isLast(),
                slice.isEmpty(),
                slice.getNumberOfElements()
        );
    }

    /**
     * 변환 함수를 적용하여 SliceResponse 생성
     * Entity를 DTO로 변환할 때 사용
     */
    public static <T, R> SliceResponse<R> from(Slice<T> slice, java.util.function.Function<T, R> mapper) {
        List<R> mappedContent = slice.getContent().stream()
                .map(mapper)
                .toList();

        return new SliceResponse<>(
                mappedContent,
                slice.getNumber(),
                slice.getSize(),
                slice.hasNext(),
                slice.hasPrevious(),
                slice.isFirst(),
                slice.isLast(),
                slice.isEmpty(),
                slice.getNumberOfElements()
        );
    }


    /**
     * 커서 기반 페이지네이션용 - 다음 커서 값 반환
     * content의 마지막 요소에서 커서값을 추출
     */
    public <C> C getNextCursor(java.util.function.Function<T, C> cursorExtractor) {
        if (content.isEmpty() || !hasNext) {
            return null;
        }
        return cursorExtractor.apply(content.get(content.size() - 1));
    }

    /**
     * 더 불러오기가 가능한지 확인
     * 모바일 앱에서 주로 사용
     */
    public boolean canLoadMore() {
        return hasNext && !empty;
    }
}