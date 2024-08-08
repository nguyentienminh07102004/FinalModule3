package com.example.demo.Service;

import com.example.demo.Model.Entity.AuthorEntity;
import com.example.demo.Model.Request.AuthorRequest;
import com.example.demo.Model.Response.AuthorResponse;

import java.util.List;
import java.util.Map;

public interface IAuthorService {
    Map<Long, String> getAll();
    List<AuthorResponse> findAndPagination(AuthorRequest request);
    void save(AuthorEntity author);
    AuthorEntity findById(Long id);
    void update(AuthorEntity author);
    void delete(Long id);
}
