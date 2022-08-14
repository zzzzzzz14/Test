package com.xxxy.zyn.action.department;

import com.alibaba.fastjson.JSONObject;
import com.xxxy.zyn.bean.Department;
import com.xxxy.zyn.dao.DepartmentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author zyn
 * @date 2022-06-01-8:53
 */
@WebServlet("/getDeptsServlet")
public class getDeptsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        DepartmentDao dao = new DepartmentDao();
        List<Department> list = dao.getDepts();
        JSONObject jo = new JSONObject();
        jo.put("data",list);
        System.out.println(jo.toJSONString());
        PrintWriter out = response.getWriter();
        out.print(jo.toString());
        out.flush();
        out.close();
        request.setAttribute("pdname",list);
    }
}
