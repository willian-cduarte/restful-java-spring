package com.will.udemy.microservices.web.models.response.pagination;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class ResponsePage<T> {

    List<T> content;
    long totalElements;
    int pageNumber;
    int pageSize;
    int totalPages;

    Boolean hasNext;

    public ResponsePage(Page<T> page) {
        super();
        this.pageNumber = page.getPageable().getPageNumber();
        this.pageSize = page.getPageable().getPageSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.content = page.getContent();
        this.hasNext = page.hasNext();
    }

    public ResponsePage(Page<?> page, List<T> content) {
        super();
        this.pageNumber = page.getPageable().getPageNumber();
        this.pageSize = page.getPageable().getPageSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.content = content;
        this.hasNext = page.hasNext();
    }


    public String toString() {
        String contentType = "UNKNOWN";
        List<T> content = this.getContent();
        if (content.size() > 0) {
            contentType = content.get(0).getClass().getName();
        }

        return String.format("Page %s of %d containing %s instances", this.getContent().size() + 1, this.getTotalPages(), contentType);
    }

}
