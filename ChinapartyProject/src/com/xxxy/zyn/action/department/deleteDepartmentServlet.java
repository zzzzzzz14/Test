package com.xxxy.zyn.action.department;

import com.xxxy.zyn.dao.DepartmentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zyn
 * @date 2022-06-01-9:45
 */
@WebServlet("/deleteDepartmentServlet")
public class deleteDepartmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html:charset=utf-8");

        //获取转发过来的id
        String cid=request.getParameter("cid");
        DepartmentDao dao = new DepartmentDao();
        //将cid 放入flag域
        String flag=dao.deleteDepartment(cid);
        //结果域放进去
        request.setAttribute("flag", flag);
        //请求重定向
        //request.getRequestDispatcher("/getAllClassesServlet").forward(request, response);
        PrintWriter out=response.getWriter();
        out.write(flag);
        out.flush();
        out.close();
    }
}
