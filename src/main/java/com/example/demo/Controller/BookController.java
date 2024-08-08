package com.example.demo.Controller;

import com.example.demo.Model.Request.BookRequest;
import com.example.demo.Service.IAuthorService;
import com.example.demo.Service.IBookService;
import com.example.demo.Service.ICategoryService;
import com.example.demo.Service.IMPL.AuthorServiceImpl;
import com.example.demo.Service.IMPL.BookServiceImpl;
import com.example.demo.Service.IMPL.CategoryServiceImpl;
import com.example.demo.Utils.BeanUtil;
import com.example.demo.Utils.ImageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "bookController", urlPatterns = {"/books/*"})
public class BookController extends HttpServlet {
    private IBookService bookService;
    private ICategoryService categoryService;
    private IAuthorService authorService;
    @Override
    public void init() throws ServletException {
        bookService = BookServiceImpl.getInstance();
        authorService = AuthorServiceImpl.getInstance();
        categoryService = CategoryServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        System.out.println(url);
        switch (url) {
            case "/":
                BookRequest bookRequest = new BookRequest();
                try {
                    bookRequest.setLimit(Integer.valueOf(req.getParameter("limit")));
                    bookRequest.setCurrentPage(Integer.valueOf(req.getParameter("currentPage")));
                } catch (NumberFormatException exception) {
                    bookRequest.setLimit(null);
                    bookRequest.setCurrentPage(null);
                }
                req.setAttribute("booksList", bookService.findAllPagination(bookRequest));
                req.setAttribute("book", bookRequest);
                req.getRequestDispatcher("/views/Books/BookHomePage.jsp").forward(req, resp);
                break;
            case "/create":
                req.setAttribute("authorList", authorService.getAll());
                req.setAttribute("categoryList", categoryService.findAll());
                req.getRequestDispatcher("/views/Books/AddBook.jsp").forward(req, resp);
                break;
            case "/delete":
                String id = req.getParameter("id");
                System.out.println(id);
                try {
                    bookService.delete(Long.valueOf(id));
                } catch (NumberFormatException exception) {
                    System.out.println("Không nhập bừa id !!");
                }
                resp.sendRedirect("/books/");
                break;
            case "/update":
                String Id = req.getParameter("id");
                try {
                    BookRequest bookRequest1 = bookService.findById(Long.valueOf(Id));
                    req.setAttribute("book", bookRequest1);
                    req.setAttribute("authorList", authorService.getAll());
                    req.setAttribute("categoryList", categoryService.findAll());
                    req.getRequestDispatcher("/views/Books/AddBook.jsp").forward(req, resp);
                } catch (NumberFormatException exception) {
                    System.out.println(exception.getMessage());
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        switch (url) {
            case "/create":
                BookRequest bookRequest = new BookRequest();
                BeanUtil.map(req, bookRequest);
                System.out.println(bookRequest.toString());
                bookRequest.setImage(ImageUtil.saveImage(req));
                bookService.save(bookRequest);
                resp.sendRedirect("/books/");
                break;
            case "/update":
                BookRequest bookRequest1 = new BookRequest();
                BeanUtil.map(req, bookRequest1);
                bookService.update(req, bookRequest1);
                resp.sendRedirect("/books/");
        }
    }
}
