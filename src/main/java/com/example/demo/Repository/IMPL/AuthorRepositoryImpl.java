package com.example.demo.Repository.IMPL;

import com.example.demo.Mapper.IMPL.AuthorMapper;
import com.example.demo.Model.Entity.AuthorEntity;
import com.example.demo.Repository.DAO.IMPL.AbstractDAO;
import com.example.demo.Repository.IAuthorRepository;

import java.util.List;
import java.util.Optional;

public class AuthorRepositoryImpl extends AbstractDAO<AuthorEntity> implements IAuthorRepository {

    private static AuthorRepositoryImpl authorRepository;
    private AuthorRepositoryImpl() {}
    public static AuthorRepositoryImpl  getInstance() {
        if(authorRepository == null) authorRepository = new AuthorRepositoryImpl();
        return authorRepository;
    }
    @Override
    public Optional<AuthorEntity> findById(Long id) {
        String sql = "SELECT * FROM authors WHERE id = ?";
        List<AuthorEntity> list = query(sql, AuthorMapper.getInstance(), id);
        if(list == null || list.isEmpty()) return Optional.empty();
        return Optional.of(list.get(0));
    }

    @Override
    public List<AuthorEntity> findAll() {
        String sql = "SELECT * FROM authors";
        return query(sql, AuthorMapper.getInstance());
    }

    @Override
    public List<AuthorEntity> findAndPagination(Integer limit, Integer offset) {
        String sql = "SELECT * FROM authors LIMIT ? OFFSET ?";
        return query(sql, AuthorMapper.getInstance(), limit, offset);
    }

    @Override
    public Long save(AuthorEntity author) {
        String sql = "INSERT INTO authors(firstname, lastname, dateOfBirth, address) VALUES (?, ?, ?, ?)";
        return insert(sql, author.getFirstname(), author.getLastname(), author.getDateOfBirth(), author.getAddress());
    }

    @Override
    public void update(AuthorEntity author) {
        String sql = "UPDATE authors SET firstname = ?, lastname = ?, address = ?, dateOfBirth = ? WHERE id = ?";
        update(sql, author.getFirstname(), author.getLastname(), author.getAddress(), author.getDateOfBirth(), author.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM authors WHERE id = ?";
        update(sql, id);
    }

}
