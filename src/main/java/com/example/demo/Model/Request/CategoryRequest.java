package com.example.demo.Model.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest extends BaseRequest {
    private Long id;
    private String name;
}
