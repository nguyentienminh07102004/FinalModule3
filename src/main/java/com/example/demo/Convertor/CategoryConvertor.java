package com.example.demo.Convertor;

import com.example.demo.Model.Entity.CategoryEntity;
import com.example.demo.Model.Request.CategoryRequest;

public class CategoryConvertor {
    public static CategoryEntity toCategoryEntity(CategoryRequest categoryRequest) {
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryRequest.getName());
        return category;
    }
}
