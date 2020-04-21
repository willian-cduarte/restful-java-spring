package com.will.udemy.microservices.web.models.response.pager;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PaginationResponse<T> {

    List<T> content;
    int pageSize;
    int pageNumber;
    Long totalElements;
    int totalPages;
    Boolean hasNext;

    public PaginationResponse(Page<T> pages) {
        super();
        content = pages.getContent();
        pageSize = pages.getSize();
        pageNumber = pages.getNumber();
        totalElements = pages.getTotalElements();
        totalPages = pages.getTotalPages();
        hasNext = pages.hasNext();
    }


}
