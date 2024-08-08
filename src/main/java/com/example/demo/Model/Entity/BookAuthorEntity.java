package com.example.demo.Model.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookAuthorEntity {
    private Long id;
    private Long bookId;
    private Long authorId;
}
