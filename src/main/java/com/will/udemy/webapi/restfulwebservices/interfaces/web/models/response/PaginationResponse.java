package com.will.udemy.webapi.restfulwebservices.interfaces.web.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginationResponse<t> {

    List<t> content;
    int pageSize;
    int pageNumber;
    Long totalElements;
    int totalPages;
    Boolean isEmpty;

}
