package com.example.demo.Service.IMPL;

import com.example.demo.Convertor.AuthorConvertor;
import com.example.demo.Model.Entity.AuthorEntity;
import com.example.demo.Model.Request.AuthorRequest;
import com.example.demo.Model.Response.AuthorResponse;
import com.example.demo.Repository.IAuthorRepository;
import com.example.demo.Repository.IMPL.AuthorRepositoryImpl;
import com.example.demo.Service.IAuthorService;
import com.example.demo.Utils.PaginationUtil;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AuthorServiceImpl implements IAuthorService {
    private static AuthorServiceImpl authorService;
    private AuthorServiceImpl() {}
    public static AuthorServiceImpl getInstance() {
        if(authorService == null) authorService = new AuthorServiceImpl();
        return authorService;
    }
    private IAuthorRepository authorRepository = AuthorRepositoryImpl.getInstance();
    @Override
    public Map<Long, String> getAll() {
        List<AuthorEntity> list = authorRepository.findAll();
        Map<Long, String> authorList = new TreeMap<>();
        list.forEach(item -> authorList.put(item.getId(), item.getFirstname() + " " + item.getLastname()));
        return authorList;
    }

    @Override
    public List<AuthorResponse> findAndPagination(AuthorRequest request) {
        Integer total = authorRepository.findAll() == null ? 0 : authorRepository.findAll().size();
        Integer offset = PaginationUtil.pagination(1, total, 2, request);
        List<AuthorEntity> authorEntities = authorRepository.findAndPagination(request.getLimit(), offset);
        return authorEntities.stream().map(AuthorConvertor::toAuthorResponse).collect(Collectors.toList());
    }

    @Override
    public void save(AuthorEntity author) {
        authorRepository.save(author);
    }

    @Override
    public AuthorEntity findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public void update(AuthorEntity author) {
        authorRepository.update(author);
    }

    @Override
    public void delete(Long id) {
        authorRepository.delete(id);
    }
}
