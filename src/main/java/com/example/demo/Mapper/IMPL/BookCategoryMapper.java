package com.example.demo.Mapper.IMPL;

import com.example.demo.Mapper.IMapper;
import com.example.demo.Model.Entity.BookCategoryEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookCategoryMapper implements IMapper<BookCategoryEntity> {
    private static BookCategoryMapper bookCategoryMapper;
    private BookCategoryMapper() {}
    public static BookCategoryMapper getInstance() {
        if(bookCategoryMapper == null) bookCategoryMapper = new BookCategoryMapper();
        return bookCategoryMapper;
    }
    @Override
    public BookCategoryEntity rowMapper(ResultSet resultSet) {
        try {
            BookCategoryEntity bookCategory = new BookCategoryEntity();
            bookCategory.setId(resultSet.getLong("id"));
            bookCategory.setCategoryId(resultSet.getLong("categoryid"));
            bookCategory.setBookId(resultSet.getLong("bookid"));
            return bookCategory;
        } catch (SQLException e) {
            return null;
        }
    }
}
