package com.chornarin.site.full_stack.Response;

import lombok.Data;

@Data
public class Pagination {

    private Integer page;
    private Integer size;

    private Boolean hasNext;
    private Boolean hasPrevious;

    private Integer totalPages;
    private Long totalElements;

}
