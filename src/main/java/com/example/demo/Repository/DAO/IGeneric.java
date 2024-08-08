package com.example.demo.Repository.DAO;

import com.example.demo.Mapper.IMapper;

import java.util.List;

public interface IGeneric<T> {
    List<T> query(String sql, IMapper<T> mapper, Object... params);
    Long insert(String sql, Object... params);
    void update(String sql, Object... params);
}
