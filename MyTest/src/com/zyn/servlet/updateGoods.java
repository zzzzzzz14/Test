package com.zyn.servlet;

import com.zyn.dao.Goodsdao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zyn
 * @date 2022-05-26-18:14
 */
public class updateGoods extends BaseServlet{
    protected void increase(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Goodsdao dao = new Goodsdao();
        int flag = dao.increase(id);
//        异步接受成功时，需要servlet输出值；
        PrintWriter out = resp.getWriter();
        out.print(flag);//jsp页面接受的值；
    }
    protected void reduce(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Goodsdao dao = new Goodsdao();
        PrintWriter out = resp.getWriter();
        out.print(dao.reduce(id));
    }
    protected void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Goodsdao dao = new Goodsdao();
        PrintWriter out = resp.getWriter();
        out.print(dao.delete(id));
    }
}
