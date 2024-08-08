package com.example.demo.Service;

import com.example.demo.Model.Request.BookRequest;
import com.example.demo.Model.Response.BookResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IBookService {
    List<BookResponse> findAll();
    List<BookResponse> findAllPagination(BookRequest bookRequest);
    BookRequest findById(Long id);
    void save(BookRequest bookRequest);
    void delete(Long id);
    void update(HttpServletRequest req, BookRequest bookRequest);
    Integer totalBooksAll();
}
