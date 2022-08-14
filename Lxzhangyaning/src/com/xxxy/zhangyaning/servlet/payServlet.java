package com.xxxy.zhangyaning.servlet;

import com.xxxy.zhangyaning.model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zyn
 * @date 2022-06-23-17:43
 */
public class payServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String press = req.getParameter("press");
        float price = Float.parseFloat(req.getParameter("price"));
        float num = Float.parseFloat(req.getParameter("num"));
        float ans = price*num;
        Book book = new Book(name,author,press,price,num);
        req.setAttribute("Book",book);
        req.setAttribute("ans",ans);
        req.getRequestDispatcher("/Succ.jsp").forward(req,resp);
    }
}
