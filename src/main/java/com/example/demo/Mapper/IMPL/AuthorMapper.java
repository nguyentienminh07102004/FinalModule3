package com.example.demo.Mapper.IMPL;

import com.example.demo.Mapper.IMapper;
import com.example.demo.Model.Entity.AuthorEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements IMapper<AuthorEntity> {
    private static AuthorMapper authorMapper;
    private AuthorMapper() {}
    public static AuthorMapper getInstance() {
        if(authorMapper == null) authorMapper = new AuthorMapper();
        return authorMapper;
    }
    @Override
    public AuthorEntity rowMapper(ResultSet resultSet) {
        AuthorEntity author = new AuthorEntity();
        try {
            author.setId(resultSet.getLong("id"));
            author.setFirstname(resultSet.getString("firstname"));
            author.setLastname(resultSet.getString("lastname"));
            author.setDateOfBirth(resultSet.getDate("dateOfBirth"));
            author.setAddress(resultSet.getString("address"));
            return author;
        } catch (SQLException e) {
            return null;
        }
    }
}
