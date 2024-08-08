package com.example.demo.Model.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponse {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String categories;
    private String authors;
    private String status;
    private String image;
}
