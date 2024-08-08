package com.example.demo.Mapper.IMPL;

import com.example.demo.Mapper.IMapper;
import com.example.demo.Model.Entity.CategoryEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements IMapper<CategoryEntity> {

    private static CategoryMapper categoryMapper;

    private CategoryMapper() {
    }

    public static CategoryMapper getCategoryMapper() {
        if(categoryMapper == null) categoryMapper = new CategoryMapper();
        return categoryMapper;
    }
    @Override
    public CategoryEntity rowMapper(ResultSet resultSet) {
        try {
            CategoryEntity category = new CategoryEntity();
            category.setId(resultSet.getLong("id"));
            category.setName(resultSet.getString("name"));
            return category;
        } catch (SQLException exception) {
            return null;
        }
    }
}
