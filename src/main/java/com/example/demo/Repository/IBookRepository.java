package com.example.demo.Repository;

import com.example.demo.Model.Entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface IBookRepository {
    List<BookEntity> findAll();
    List<BookEntity> findAllPagination(Integer limit, Integer offset);
    Long save(BookEntity bookEntity);
    Optional<BookEntity> findById(Long id);
    void deleteById(Long id);
    void update(BookEntity book);
}
