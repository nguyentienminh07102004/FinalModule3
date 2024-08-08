package com.example.demo.Controller;

import com.example.demo.Model.Request.CategoryRequest;
import com.example.demo.Service.ICategoryService;
import com.example.demo.Service.IMPL.CategoryServiceImpl;
import com.example.demo.Utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "categoryController", urlPatterns = {"/categories/*"})
public class CategoryController extends HttpServlet {

    private ICategoryService categoryService;

    @Override
    public void init() throws ServletException {
        categoryService = CategoryServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        if(url.equalsIgnoreCase("/")) {
            CategoryRequest categoryRequest = new CategoryRequest();
            try {
                categoryRequest.setLimit(Integer.valueOf(req.getParameter("limit")));
                categoryRequest.setCurrentPage(Integer.valueOf(req.getParameter("currentPage")));
            } catch (NumberFormatException exception) {
                categoryRequest.setLimit(null);
                categoryRequest.setCurrentPage(null);
            }
            req.setAttribute("category", categoryRequest);
            req.setAttribute("categories", categoryService.findPagination(categoryRequest));
            req.getRequestDispatcher("/views/Categories/CategoryPage.jsp").forward(req, resp);
        } else if(url.equalsIgnoreCase("/create")) {
            req.getRequestDispatcher("/views/Categories/CategoriesAdd.jsp").forward(req, resp);
        } else if(url.equalsIgnoreCase("/delete")) {
            try {
                categoryService.delete(Long.valueOf(req.getParameter("id")));
            } catch(RuntimeException e) {
                HttpSession session = req.getSession();
                session.setAttribute("message", e.getMessage());
                session.setMaxInactiveInterval(10);
            }
            resp.sendRedirect("/categories/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        if(url.equalsIgnoreCase("/create")) {
            req.setCharacterEncoding("UTF-8");
            CategoryRequest request = new CategoryRequest();
            BeanUtil.map(req, request);
            categoryService.save(request);
            resp.sendRedirect("/categories/");
        }
    }
}
