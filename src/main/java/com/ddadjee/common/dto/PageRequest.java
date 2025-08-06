package com.ddadjee.common.dto;


import com.ddadjee.common.constant.PaginationConstants;
import static com.ddadjee.common.constant.PaginationConstants.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Sort;

public record PageRequest(
        @Min(value = MIN_PAGE_NUMBER, message = "{validation.page.min}")
        Integer page,

        @Min(value = MIN_PAGE_SIZE, message = "{validation.size.min}")
        @Max(value =MAX_PAGE_SIZE, message = "{validation.size.max}")
        Integer size,

        String sortBy,

        @Pattern(regexp = SORT_DIRECTION_PATTERN, message = "{validation.sort.direction}")
        String sortDirection
) {
    /**
     * 기본 생성자 - 기본값 설정
     */
    public PageRequest {
        page = page != null ? page : DEFAULT_PAGE_NUMBER;
        size = size != null ? size : DEFAULT_PAGE_SIZE;
        sortBy = sortBy != null ? sortBy : DEFAULT_SORT_BY;
        sortDirection = sortDirection != null ? sortDirection : DEFAULT_SORT_DIRECTION;
    }

    /**
     * Spring Data Pageable 객체로 변환
     */
    public org.springframework.data.domain.PageRequest toPageable() {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        return org.springframework.data.domain.PageRequest.of(
                page,
                size,
                Sort.by(direction, sortBy)
        );
    }

    /**
     * 기본 PageRequest 생성
     */
    public static PageRequest of(int page, int size) {
        return new PageRequest(page, size, null, null);
    }
}
