package com.xxxy.zyn.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author zyn
 * @date 2022-06-05-10:59
 */
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");			//统一修改编码格式
        servletResponse.setContentType("text/html;charset=utf-8");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Object username = httpServletRequest.getSession().getAttribute("username");
        if(username==null){
//            HttpServletResponse response = (HttpServletResponse) servletResponse;
//            response.sendRedirect("/Final_project/pages/user/login.jsp");
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
