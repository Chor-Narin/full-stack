package com.chornarin.site.full_stack.Response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationResponse<T> {
    private List<T> data;
    private Pagination pagination;
}
