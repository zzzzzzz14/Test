package com.xxxy.zyn.action.department;

import com.xxxy.zyn.bean.Department;
import com.xxxy.zyn.dao.DepartmentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zyn
 * @date 2022-06-02-8:09
 */
@WebServlet("/findDepartmentServlet")
public class findDepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String cid=request.getParameter("cid");
        DepartmentDao dao = new DepartmentDao();
        Department model = dao.findDepartmentById(cid);
        request.setAttribute("model",model);
        request.getRequestDispatcher("/view/department/DepartmentUpdate.jsp").forward(request,response);
    }
}
