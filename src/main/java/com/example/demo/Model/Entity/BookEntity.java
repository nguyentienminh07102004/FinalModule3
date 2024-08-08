package com.example.demo.Model.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookEntity {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private Integer status;
    private String image;
}
