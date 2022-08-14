package com.xxxy.zyn.action.department;

import com.xxxy.zyn.bean.Department;
import com.xxxy.zyn.dao.DepartmentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * @author zyn
 * @date 2022-06-01-9:13
 */
@WebServlet("/addDepartmentervlet")
public class addDepartmentervlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html:charset=utf-8");

        String cname=request.getParameter("cname");
        String cflag=request.getParameter("cflag");
        String cdate=request.getParameter("cdate");
        String pdname=request.getParameter("pdname");
        String cuuid= UUID.randomUUID().toString().replace("-", "");
        Department model = new Department();
        model.setDepartment_id(cuuid);
        model.setDepartmentName(cname);
        model.setDepartmentFlag(cflag);
        model.setDepartmentPid(pdname);
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            model.setDepartmentCDate(f.parse(cdate));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DepartmentDao dao = new DepartmentDao();
        String str = dao.addDepartment(model);
        PrintWriter out=response.getWriter();
        out.write(str);
        out.flush();
        out.close();
    }
}
