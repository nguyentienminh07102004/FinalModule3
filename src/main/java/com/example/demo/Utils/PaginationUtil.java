package com.example.demo.Utils;

import com.example.demo.Model.Request.BaseRequest;

public class PaginationUtil {
    public static Integer pagination(Integer currentPage, Integer totalItems, Integer limit, BaseRequest baseRequest) {
        if(baseRequest.getLimit() == null) baseRequest.setLimit(limit);
        Integer totalPages = (int) Math.ceil((double) totalItems / limit);
        baseRequest.setTotalPages(totalPages);
        if(baseRequest.getCurrentPage() == null || baseRequest.getCurrentPage() > totalPages) baseRequest.setCurrentPage(1);
        return (baseRequest.getCurrentPage() - 1) * baseRequest.getLimit();
    }
}
