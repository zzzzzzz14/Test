package com.zyn.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zyn
 * @date 2022-05-13-9:18
 */
public class CookieServlet extends BaseServlet{
    protected void getcookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            response.getWriter().write(cookie.getName()+"   "+cookie.getValue());
        }
    }
    protected void creat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("key1","value1");
        response.addCookie(cookie);
        Cookie cookie1 = new Cookie("key2","value2");
        response.addCookie(cookie1);
        Cookie cookie2 = new Cookie("key3","value3");
        response.addCookie(cookie2);
        response.getWriter().write("成功");
    }
}
