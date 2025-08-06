package com.ddadjee.common.dto;

import static com.ddadjee.common.constant.PaginationConstants.*;

import com.ddadjee.common.constant.PaginationConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 페이지네이션 응답 DTO
 *
 * @param <T> 콘텐츠 타입
 * @param content 페이지 콘텐츠 목록
 * @param pageNumber 현재 페이지 번호 (0부터 시작)
 * @param pageSize 페이지 크기
 * @param totalElements 전체 요소 개수
 * @param totalPages 전체 페이지 수
 * @param first 첫 페이지 여부
 * @param last 마지막 페이지 여부
 * @param empty 빈 페이지 여부
 * @param numberOfElements 현재 페이지의 요소 개수
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PageResponse<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last,
        boolean empty,
        int numberOfElements
) {
    /**
     * Spring Data Page 객체로부터 PageResponse 생성
     */
    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.isEmpty(),
                page.getNumberOfElements()
        );
    }

    /**
     * 변환 함수를 적용하여 PageResponse 생성
     * Entity를 DTO로 변환할 때 사용
     */
    public static <T, R> PageResponse<R> from(Page<T> page, java.util.function.Function<T, R> mapper) {
        List<R> mappedContent = page.getContent().stream()
                .map(mapper)
                .toList();

        return new PageResponse<>(
                mappedContent,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.isEmpty(),
                page.getNumberOfElements()
        );
    }



    /**
     * 다음 페이지 존재 여부
     */
    public boolean hasNext() {
        return !last;
    }

    /**
     * 이전 페이지 존재 여부
     */
    public boolean hasPrevious() {
        return !first;
    }
}