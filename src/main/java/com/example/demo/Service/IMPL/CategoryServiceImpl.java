package com.example.demo.Service.IMPL;

import com.example.demo.Convertor.CategoryConvertor;
import com.example.demo.Model.Entity.CategoryEntity;
import com.example.demo.Model.Request.CategoryRequest;
import com.example.demo.Repository.ICategoryRepository;
import com.example.demo.Repository.IMPL.CategoryRepositoryImpl;
import com.example.demo.Service.ICategoryService;
import com.example.demo.Utils.PaginationUtil;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CategoryServiceImpl implements ICategoryService {
    private static CategoryServiceImpl categoryService;
    private CategoryServiceImpl() {}
    public static CategoryServiceImpl getInstance() {
        if(categoryService == null) categoryService = new CategoryServiceImpl();
        return categoryService;
    }
    private ICategoryRepository categoryRepository = CategoryRepositoryImpl.getInstance();
    @Override
    public Map<Long, String> findAll() {
        List<CategoryEntity> list = categoryRepository.findAll();
        Map<Long, String> categoryList = new TreeMap<>();
        list.forEach(item -> categoryList.put(item.getId(), item.getName()));
        return categoryList;
    }

    @Override
    public List<CategoryEntity> findPagination(CategoryRequest categoryRequest) {
        Integer offset = PaginationUtil.pagination(1, countItems(), 2, categoryRequest);
        return categoryRepository.findPagination(categoryRequest.getLimit(), offset);
    }

    @Override
    public Integer countItems() {
        Map<Long, String> categories = findAll();
        return categories == null ? 0 : categories.size();
    }

    @Override
    public void save(CategoryRequest request) {
        CategoryEntity category = CategoryConvertor.toCategoryEntity(request);
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
    }
}
