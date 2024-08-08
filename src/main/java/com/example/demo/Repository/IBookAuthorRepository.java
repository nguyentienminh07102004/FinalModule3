package com.example.demo.Repository;

import com.example.demo.Model.Entity.BookAuthorEntity;

import java.util.List;

public interface IBookAuthorRepository {
    List<BookAuthorEntity> findByBookId(Long id);
    List<BookAuthorEntity> findByAuthorId(Long id);
    void save(BookAuthorEntity bookAuthor);
    void deleteByBookId(Long id);
    void deleteByAuthorId(Long id);
}
