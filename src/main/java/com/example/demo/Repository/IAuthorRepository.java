package com.example.demo.Repository;

import com.example.demo.Model.Entity.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface IAuthorRepository {
    Optional<AuthorEntity> findById(Long id);
    List<AuthorEntity> findAll();
    List<AuthorEntity> findAndPagination(Integer limit, Integer offset);
    Long save(AuthorEntity author);
    void update(AuthorEntity author);
    void delete(Long id);
}
