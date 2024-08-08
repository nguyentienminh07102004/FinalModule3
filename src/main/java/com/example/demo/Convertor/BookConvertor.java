package com.example.demo.Convertor;

import com.example.demo.Model.Entity.BookAuthorEntity;
import com.example.demo.Model.Entity.BookCategoryEntity;
import com.example.demo.Model.Entity.BookEntity;
import com.example.demo.Model.Entity.CategoryEntity;
import com.example.demo.Model.Request.BookRequest;
import com.example.demo.Model.Response.BookResponse;
import com.example.demo.Repository.IMPL.AuthorRepositoryImpl;
import com.example.demo.Repository.IMPL.BookAuthorRepositoryImpl;
import com.example.demo.Repository.IMPL.BookCategoryRepositoryImpl;
import com.example.demo.Repository.IMPL.CategoryRepositoryImpl;
import com.example.demo.Utils.StatusUtil;

import java.util.List;
import java.util.stream.Collectors;

public class BookConvertor {
    private static BookCategoryRepositoryImpl bookCategoryRepository = BookCategoryRepositoryImpl.getInstance();
    private static CategoryRepositoryImpl categoryRepository = CategoryRepositoryImpl.getInstance();
    private static BookAuthorRepositoryImpl bookAuthorRepository = BookAuthorRepositoryImpl.getInstance();
    private static AuthorRepositoryImpl authorRepository = AuthorRepositoryImpl.getInstance();
    public static BookResponse toBookResponse(BookEntity bookEntity) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(bookEntity.getId());
        bookResponse.setName(bookEntity.getName());
        bookResponse.setDescription(bookEntity.getDescription());
        bookResponse.setPrice(bookEntity.getPrice());
        bookResponse.setStatus(StatusUtil.convertStatus(bookEntity.getStatus()));
        List<BookCategoryEntity> bookCategoryEntityList = bookCategoryRepository.findByBookId(bookEntity.getId());
        if(bookCategoryEntityList != null && !bookCategoryEntityList.isEmpty()) {
            bookResponse.setCategories(bookCategoryEntityList.stream()
                    .map(BookCategoryEntity::getCategoryId)
                    .map(id -> categoryRepository.findById(id))
                    .map(item -> item.orElseThrow(() -> new RuntimeException("Không có loại sách này")))
                    .map(CategoryEntity::getName)
                    .collect(Collectors.joining(", ")));
        }
        List<BookAuthorEntity> bookAuthorEntityList = bookAuthorRepository.findByBookId(bookEntity.getId());
        if(bookAuthorEntityList != null && !bookAuthorEntityList.isEmpty()) {
            bookResponse.setAuthors(bookAuthorEntityList.stream()
                    .map(BookAuthorEntity::getAuthorId)
                    .map(id -> authorRepository.findById(id))
                    .map(item -> item.orElseThrow(() -> new RuntimeException("Không có tác giả này")))
                    .map(item -> item.getFirstname() + " " + item.getLastname())
                    .collect(Collectors.joining(", ")));
        }
        bookResponse.setImage("/Images/" + bookEntity.getImage());
        return bookResponse;
    }

    public static BookEntity toBookEntity(BookRequest bookRequest) {
        BookEntity book = new BookEntity();
        if(bookRequest.getId() != null) book.setId(bookRequest.getId());
        book.setName(bookRequest.getName());
        book.setPrice(bookRequest.getPrice());
        book.setStatus(bookRequest.getStatus());
        book.setDescription(bookRequest.getDescription());
        book.setImage(bookRequest.getImage());
        return book;
    }

    public static BookRequest toBookRequest(BookEntity book) {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setId(book.getId());
        bookRequest.setName(book.getName());
        bookRequest.setPrice(book.getPrice());
        bookRequest.setDescription(book.getDescription());
        bookRequest.setStatus(book.getStatus());
        bookRequest.setAuthorIds(bookAuthorRepository.findByBookId(book.getId()).stream()
                .map(BookAuthorEntity::getAuthorId)
                .collect(Collectors.toList()));
        bookRequest.setCategoryIds(bookCategoryRepository.findByBookId(book.getId()).stream()
                .map(BookCategoryEntity::getCategoryId)
                .collect(Collectors.toList()));
        bookRequest.setImage(book.getImage());
        return bookRequest;
    }
}
