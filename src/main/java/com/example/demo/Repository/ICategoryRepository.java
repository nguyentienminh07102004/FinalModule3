package com.example.demo.Repository;

import com.example.demo.Model.Entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository {
    List<CategoryEntity> findAll();
    Optional<CategoryEntity> findById(Long id);
    List<CategoryEntity> findPagination(Integer limit, Integer offset);
    Long save(CategoryEntity categoryEntity);
    void delete(Long id);
}
