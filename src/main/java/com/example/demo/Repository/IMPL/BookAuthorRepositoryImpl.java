package com.example.demo.Repository.IMPL;

import com.example.demo.Mapper.IMPL.BookAuthorMapper;
import com.example.demo.Model.Entity.BookAuthorEntity;
import com.example.demo.Repository.DAO.IMPL.AbstractDAO;
import com.example.demo.Repository.IBookAuthorRepository;

import java.util.List;

public class BookAuthorRepositoryImpl extends AbstractDAO<BookAuthorEntity> implements IBookAuthorRepository {
    private static BookAuthorRepositoryImpl bookAuthorRepository;
    private BookAuthorRepositoryImpl() {}
    public static BookAuthorRepositoryImpl getInstance() {
        if(bookAuthorRepository == null) bookAuthorRepository = new BookAuthorRepositoryImpl();
        return bookAuthorRepository;
    }
    @Override
    public List<BookAuthorEntity> findByBookId(Long id) {
        String sql = "SELECT * FROM author_book WHERE bookId = ?";
        return query(sql, BookAuthorMapper.getInstance(), id);
    }

    @Override
    public List<BookAuthorEntity> findByAuthorId(Long id) {
        String sql = "SELECT * FROM author_book WHERE authorId = ?";
        return query(sql, BookAuthorMapper.getInstance(), id);
    }

    @Override
    public void save(BookAuthorEntity bookAuthor) {
        String sql = "INSERT INTO author_book(bookId, authorId) VALUES(?, ?)";
        insert(sql, bookAuthor.getBookId(), bookAuthor.getAuthorId());
    }

    @Override
    public void deleteByBookId(Long id) {
        String sql = "DELETE FROM author_book WHERE bookId = ?";
        update(sql, id);
    }

    @Override
    public void deleteByAuthorId(Long id) {
        String sql = "DELETE FROM author_book WHERE authorId = ?";
        update(sql, id);
    }
}
