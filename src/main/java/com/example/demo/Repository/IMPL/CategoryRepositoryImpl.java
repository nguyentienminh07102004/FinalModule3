package com.example.demo.Repository.IMPL;

import com.example.demo.Mapper.IMPL.CategoryMapper;
import com.example.demo.Model.Entity.CategoryEntity;
import com.example.demo.Repository.DAO.IMPL.AbstractDAO;
import com.example.demo.Repository.ICategoryRepository;

import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl extends AbstractDAO<CategoryEntity> implements ICategoryRepository {
    private static CategoryRepositoryImpl categoryRepository;
    private CategoryRepositoryImpl() {
    }

    public static CategoryRepositoryImpl getInstance() {
        if(categoryRepository == null) categoryRepository = new CategoryRepositoryImpl();
        return categoryRepository;
    }
    @Override
    public List<CategoryEntity> findAll() {
        String sql = "SELECT * FROM categories";
        return query(sql, CategoryMapper.getCategoryMapper());
    }

    @Override
    public Optional<CategoryEntity> findById(Long id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        List<CategoryEntity> list = query(sql, CategoryMapper.getCategoryMapper(), id);
        if(list == null || list.isEmpty()) return Optional.empty();
        return Optional.of(list.get(0));
    }

    @Override
    public List<CategoryEntity> findPagination(Integer limit, Integer offset) {
        String sql = "SELECT * FROM categories LIMIT ? OFFSET ?";
        return query(sql, CategoryMapper.getCategoryMapper(), limit, offset);
    }

    @Override
    public Long save(CategoryEntity categoryEntity) {
        String sql = "INSERT INTO categories(name) VALUES (?)";
        return insert(sql, categoryEntity.getName());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM categories WHERE id = ?";
        update(sql, id);
    }


}
