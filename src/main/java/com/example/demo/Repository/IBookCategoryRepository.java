package com.example.demo.Repository;

import com.example.demo.Model.Entity.BookCategoryEntity;

import java.util.List;

public interface IBookCategoryRepository {
    List<BookCategoryEntity> findByBookId(Long id);
    List<BookCategoryEntity> findByCategoryId(Long id);
    void save(BookCategoryEntity bookCategory);
    void deleteByBookId(Long id);
    void deleteByCategoryId(Long id);
}
