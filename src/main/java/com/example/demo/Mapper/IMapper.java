package com.example.demo.Mapper;

import java.sql.ResultSet;

public interface IMapper<T> {
    T rowMapper(ResultSet resultSet);
}
