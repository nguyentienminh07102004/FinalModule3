package com.example.demo.Model.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRequest {
    private Integer currentPage;
    private Integer totalPages;
    private Integer limit;
}
