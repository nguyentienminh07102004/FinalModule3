package com.example.demo.Service.IMPL;

import com.example.demo.Convertor.BookConvertor;
import com.example.demo.Model.Entity.BookAuthorEntity;
import com.example.demo.Model.Entity.BookCategoryEntity;
import com.example.demo.Model.Entity.BookEntity;
import com.example.demo.Model.Request.BookRequest;
import com.example.demo.Model.Response.BookResponse;
import com.example.demo.Repository.IBookAuthorRepository;
import com.example.demo.Repository.IBookCategoryRepository;
import com.example.demo.Repository.IMPL.BookAuthorRepositoryImpl;
import com.example.demo.Repository.IMPL.BookCategoryRepositoryImpl;
import com.example.demo.Repository.IMPL.BookRepositoryImpl;
import com.example.demo.Repository.IBookRepository;
import com.example.demo.Service.IBookService;
import com.example.demo.Utils.ImageUtil;
import com.example.demo.Utils.PaginationUtil;
import com.example.demo.Utils.ValidateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookServiceImpl implements IBookService {
    private IBookRepository bookRepository = BookRepositoryImpl.getInstance();
    private IBookCategoryRepository bookCategoryRepository = BookCategoryRepositoryImpl.getInstance();
    private IBookAuthorRepository bookAuthorRepository = BookAuthorRepositoryImpl.getInstance();

    private static BookServiceImpl bookService;
    private BookServiceImpl() {}
    public static BookServiceImpl getInstance() {
        if(bookService == null) bookService = new BookServiceImpl();
        return bookService;
    }

    @Override
    public List<BookResponse> findAll() {
        List<BookEntity> list = bookRepository.findAll();
        if(list == null) return new ArrayList<>();
        return list.stream().map(BookConvertor::toBookResponse).collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> findAllPagination(BookRequest bookRequest) {
        Integer offset = PaginationUtil.pagination(1, totalBooksAll(), 3, bookRequest);
        Integer limit = bookRequest.getLimit();
        List<BookEntity> list = bookRepository.findAllPagination(limit, offset);
        if(list == null) return new ArrayList<>();
        return list.stream().map(BookConvertor::toBookResponse).collect(Collectors.toList());
    }

    @Override
    public BookRequest findById(Long id) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return BookConvertor.toBookRequest(book);
    }

    @Override
    public void save(BookRequest bookRequest) {
        // Lưu sách
        BookEntity book = BookConvertor.toBookEntity(bookRequest);
        ValidateUtil.check(book);
        Long bookId = bookRepository.save(book);
        // lưu book_category
        List<Long> categoryIds = bookRequest.getCategoryIds();
        if(categoryIds != null && !categoryIds.isEmpty()) {
            for (Long id : categoryIds) {
                BookCategoryEntity bookCategory = new BookCategoryEntity();
                bookCategory.setBookId(bookId);
                bookCategory.setCategoryId(id);
                bookCategoryRepository.save(bookCategory);
            }
        }
        // lưu vào book_author
        List<Long> authorIds = bookRequest.getAuthorIds();
        if(authorIds != null && !authorIds.isEmpty()) {
            for (Long authorId : authorIds) {
                BookAuthorEntity bookAuthor = new BookAuthorEntity();
                bookAuthor.setAuthorId(authorId);
                bookAuthor.setBookId(bookId);
                bookAuthorRepository.save(bookAuthor);
            }
        }
    }

    @Override
    public void delete(Long id) {
        // Kiểm tra sự tồn tại
        bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Không có quyển sách này đê xoá"));
        bookRepository.deleteById(id);
    }

    @Override
    public void update(HttpServletRequest req, BookRequest bookRequest) {
        try {
            BookEntity book = BookConvertor.toBookEntity(bookRequest);
            // update ảnh
            boolean isUploadImage = req.getPart("image") == null;
            String image = findById(bookRequest.getId()).getImage();
            if(!isUploadImage && image != null && !image.isEmpty()) {
                // xoá ảnh cũ
                ImageUtil.deleteFileImage(req, image);
            } else if(isUploadImage && image != null && !image.isEmpty()) {
                // cập nhật ảnh cũ
                book.setImage(findById(book.getId()).getImage());
            }
            String newImage = ImageUtil.saveImage(req);
            book.setImage(newImage);
            // update sách
            bookRepository.update(book);
            // xoá data cũ
            List<BookCategoryEntity> bookCategorys = bookCategoryRepository.findByBookId(bookRequest.getId());
            if (bookCategorys != null && !bookCategorys.isEmpty()) {
                bookCategoryRepository.deleteByBookId(book.getId());
            }
            List<BookAuthorEntity> bookAuthors = bookAuthorRepository.findByBookId(book.getId());
            if (bookAuthors != null && !bookAuthors.isEmpty()) {
                bookAuthorRepository.deleteByBookId(book.getId());
            }
            // insert data mới
            // lưu book_category
            List<Long> categoryIds = bookRequest.getCategoryIds();
            for (Long id : categoryIds) {
                BookCategoryEntity bookCategory = new BookCategoryEntity();
                bookCategory.setBookId(book.getId());
                bookCategory.setCategoryId(id);
                bookCategoryRepository.save(bookCategory);
            }
            // lưu vào book_author
            List<Long> authorIds = bookRequest.getAuthorIds();
            for (Long authorId : authorIds) {
                BookAuthorEntity bookAuthor = new BookAuthorEntity();
                bookAuthor.setAuthorId(authorId);
                bookAuthor.setBookId(book.getId());
                bookAuthorRepository.save(bookAuthor);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Integer totalBooksAll() {
        List<BookResponse> list = findAll();
        if(list != null) return list.size();
        return 0;
    }
}
