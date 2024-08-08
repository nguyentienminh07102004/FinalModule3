package com.example.demo.Controller;

import com.example.demo.Model.Entity.AuthorEntity;
import com.example.demo.Model.Request.AuthorRequest;
import com.example.demo.Model.Response.AuthorResponse;
import com.example.demo.Service.IAuthorService;
import com.example.demo.Service.IMPL.AuthorServiceImpl;
import com.example.demo.Utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "authorController", urlPatterns = {"/authors/*"})
public class AuthorController extends HttpServlet {
    private IAuthorService authorService;

    @Override
    public void init() throws ServletException {
        authorService = AuthorServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        if(url.equalsIgnoreCase("/")) {
            AuthorRequest request = new AuthorRequest();
            Integer limit = 2;
            Integer currentPage = 1;
            try {
                limit = Integer.valueOf(req.getParameter("limit"));
                currentPage = Integer.valueOf(req.getParameter("currentPage"));
            } catch (Exception e) {
                limit = 2;
                currentPage = 1;
            }

            request.setLimit(limit);
            request.setCurrentPage(currentPage);
            List<AuthorResponse> list = authorService.findAndPagination(request);
            req.setAttribute("authors", list);
            req.setAttribute("pagination", request);
            req.getRequestDispatcher("/views/Authors/author-list.jsp").forward(req, resp);
        } else if(url.equalsIgnoreCase("/create")) {
            req.getRequestDispatcher("/views/Authors/addAuthor.jsp").forward(req, resp);
        } else if(url.equalsIgnoreCase("/update")) {
            req.setAttribute("author", authorService.findById(Long.valueOf(req.getParameter("id"))));
            req.getRequestDispatcher("/views/Authors/addAuthor.jsp").forward(req, resp);
        } else if(url.equalsIgnoreCase("/delete")) {
            try {
                authorService.delete(Long.valueOf(req.getParameter("id")));
            } catch (RuntimeException e) {
                HttpSession session = req.getSession();
                session.setAttribute("message", e.getMessage());
                session.setMaxInactiveInterval(10);
            }
            resp.sendRedirect("/authors/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        req.setCharacterEncoding("UTF-8");
        if(url.equalsIgnoreCase("/create")) {
            AuthorEntity author = new AuthorEntity();
            BeanUtil.map(req, author);
            authorService.save(author);
            resp.sendRedirect("/authors/");
        } else if (url.equalsIgnoreCase("/update")) {
            AuthorEntity author = new AuthorEntity();
            BeanUtil.map(req, author);
            authorService.update(author);
            resp.sendRedirect("/authors/");
        }
    }
}
