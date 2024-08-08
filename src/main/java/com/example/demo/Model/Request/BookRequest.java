package com.example.demo.Model.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BookRequest extends BaseRequest {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer status;
    private List<Long> authorIds;
    private List<Long> categoryIds;
    private String image;
}
