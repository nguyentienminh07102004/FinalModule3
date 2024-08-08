package com.example.demo.Mapper.IMPL;

import com.example.demo.Mapper.IMapper;
import com.example.demo.Model.Entity.BookEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements IMapper<BookEntity> {

    private static BookMapper bookMapper;

    private BookMapper() {
    }

    public static BookMapper getBookMapper() {
        if(bookMapper == null) bookMapper = new BookMapper();
        return bookMapper;
    }

    @Override
    public BookEntity rowMapper(ResultSet resultSet) {
        try {
            BookEntity bookEntity = new BookEntity();
            bookEntity.setId(resultSet.getLong("id"));
            bookEntity.setName(resultSet.getString("name"));
            bookEntity.setPrice(resultSet.getInt("price"));
            bookEntity.setDescription(resultSet.getString("description"));
            bookEntity.setStatus(resultSet.getInt("status"));
            bookEntity.setImage(resultSet.getString("image"));
            return bookEntity;
        } catch (SQLException e) {
            return null;
        }
    }
}
