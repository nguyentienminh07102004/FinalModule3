package com.example.demo.Mapper.IMPL;

import com.example.demo.Mapper.IMapper;
import com.example.demo.Model.Entity.BookAuthorEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookAuthorMapper implements IMapper<BookAuthorEntity> {
    private static BookAuthorMapper bookAuthorMapper;
    private BookAuthorMapper() {}
    public static BookAuthorMapper getInstance() {
        if(bookAuthorMapper == null) bookAuthorMapper = new BookAuthorMapper();
        return bookAuthorMapper;
    }
    @Override
    public BookAuthorEntity rowMapper(ResultSet resultSet) {
        try {
            BookAuthorEntity bookAuthor = new BookAuthorEntity();
            bookAuthor.setId(resultSet.getLong("id"));
            bookAuthor.setAuthorId(resultSet.getLong("authorid"));
            bookAuthor.setBookId(resultSet.getLong("bookid"));
            return bookAuthor;
        } catch (SQLException e) {
            return null;
        }
    }
}