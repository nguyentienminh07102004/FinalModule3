package com.example.demo.Service;

import com.example.demo.Model.Entity.CategoryEntity;
import com.example.demo.Model.Request.CategoryRequest;

import java.util.List;
import java.util.Map;

public interface ICategoryService {
    Map<Long, String> findAll();
    List<CategoryEntity> findPagination(CategoryRequest categoryRequest);
    Integer countItems();
    void save(CategoryRequest request);
    void delete(Long id);
}
