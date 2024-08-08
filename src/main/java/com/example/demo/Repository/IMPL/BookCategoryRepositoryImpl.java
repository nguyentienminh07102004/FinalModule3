package com.example.demo.Repository.IMPL;

import com.example.demo.Mapper.IMPL.BookCategoryMapper;
import com.example.demo.Model.Entity.BookCategoryEntity;
import com.example.demo.Repository.DAO.IMPL.AbstractDAO;
import com.example.demo.Repository.IBookCategoryRepository;

import java.util.List;

public class BookCategoryRepositoryImpl extends AbstractDAO<BookCategoryEntity> implements IBookCategoryRepository {
    private static BookCategoryRepositoryImpl bookCategory;
    private BookCategoryRepositoryImpl() {}
    public static BookCategoryRepositoryImpl getInstance() {
        if(bookCategory == null) bookCategory = new BookCategoryRepositoryImpl();
        return bookCategory;
    }
    @Override
    public List<BookCategoryEntity> findByBookId(Long id) {
        String sql = "SELECT * FROM category_book WHERE bookId = ?";
        return query(sql, BookCategoryMapper.getInstance(), id);
    }

    @Override
    public List<BookCategoryEntity> findByCategoryId(Long id) {
        String sql = "SELECT * FROM category_book WHERE categoryId = ?";
        return query(sql, BookCategoryMapper.getInstance(), id);
    }

    @Override
    public void save(BookCategoryEntity bookCategory) {
        String sql = "INSERT INTO category_book(bookId, categoryId) VALUES(?, ?)";
        insert(sql, bookCategory.getBookId(), bookCategory.getCategoryId());
    }

    @Override
    public void deleteByBookId(Long id) {
        String sql = "DELETE FROM category_book WHERE bookId = ?";
        update(sql, id);
    }

    @Override
    public void deleteByCategoryId(Long id) {
        String sql = "DELETE FROM category_book WHERE categoryId = ?";
        update(sql, id);
    }
}
