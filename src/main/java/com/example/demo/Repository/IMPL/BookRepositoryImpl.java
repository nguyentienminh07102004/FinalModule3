package com.example.demo.Repository.IMPL;

import com.example.demo.Mapper.IMPL.BookMapper;
import com.example.demo.Model.Entity.BookEntity;
import com.example.demo.Repository.DAO.IMPL.AbstractDAO;
import com.example.demo.Repository.IBookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl extends AbstractDAO<BookEntity> implements IBookRepository {

    private static BookRepositoryImpl bookRepository;
    private BookRepositoryImpl() {}
    public static BookRepositoryImpl getInstance() {
        if(bookRepository == null) bookRepository = new BookRepositoryImpl();
        return bookRepository;
    }

    @Override
    public List<BookEntity> findAll() {
        String sql = "SELECT * FROM books WHERE status <> 0";
        return query(sql, BookMapper.getBookMapper());
    }

    @Override
    public List<BookEntity> findAllPagination(Integer limit, Integer offset) {
        String sql = "SELECT * FROM books WHERE status <> 0 LIMIT ? OFFSET ?";
        return query(sql, BookMapper.getBookMapper(), limit, offset);
    }

    @Override
    public Long save(BookEntity bookEntity) {
        String sql = "INSERT INTO books (name, description, price, status, image) VALUES(?, ?, ?, ?, ?)";
        return insert(sql, bookEntity.getName(), bookEntity.getDescription(), bookEntity.getPrice(), bookEntity.getStatus(), bookEntity.getImage());
    }

    @Override
    public Optional<BookEntity> findById(Long id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        List<BookEntity> list = query(sql, BookMapper.getBookMapper(), id);
        if(list == null || list.isEmpty())
            return Optional.empty();
        return Optional.of(list.get(0));
    }

    @Override
    public void deleteById(Long id) {
        String sql = "UPDATE books SET status = 0 WHERE id = ?";
        update(sql, id);
    }

    @Override
    public void update(BookEntity book) {
        String sql = "UPDATE books SET name = ?, description = ?, status = ?, price = ?, image = ? WHERE id = ?";
        update(sql, book.getName(), book.getDescription(), book.getStatus(), book.getPrice(), book.getImage(), book.getId());
    }
}
