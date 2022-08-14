package com.zyn.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author zyn
 * @date 2022-05-13-19:26
 */
public class SessionServlet extends  BaseServlet{
    protected void set(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("key1","value1");
        response.getWriter().write("存入成功");
    }
    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object attribute = request.getSession().getAttribute("key1");
        response.getWriter().write( "get" + attribute );
    }
    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean isNew = session.isNew();
        String id = session.getId();
        response.getWriter().write(isNew+"    "+id +"<br/>");
    }
}
