package com.xxxy.zyn.action.department;

import com.alibaba.fastjson.JSONObject;
import com.xxxy.zyn.bean.Department;
import com.xxxy.zyn.bean.Page;
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
 * @date 2022-05-31-9:30
 */
@WebServlet("/getAllDepartmentBYPageServlet")
public class getAllDepartmentBYPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=UTF-8");
        DepartmentDao dao = new DepartmentDao();
        String cflag=request.getParameter("cflag");
        String sdate=request.getParameter("sdate");
        String edate=request.getParameter("edate");
        String cname=request.getParameter("cname");
        System.out.println(cflag);
        System.out.println(sdate);
        System.out.println(edate);

        StringBuffer str=new StringBuffer();
        if(cflag!=null&&!cflag.equals("")){
            if(cflag.equals("1")||cflag.equals("0")){
                str.append(" and d1.departmentFlag="+cflag);
            }
        }
        if(sdate!=null&&!sdate.equals("")){
            str.append(" and d1.departmentCDate>="+sdate);
        }
        if(edate!=null&&!edate.equals("")){
            str.append(" and d1.departmentCDate<="+edate);
        }

        if(cname!=null&&!cname.equals("")){
//            byte[] bytes = cname.getBytes(StandardCharsets.ISO_8859_1);
//            //3.2 字节数组解码
//            cname = new String(bytes, StandardCharsets.UTF_8);
//            cname=new String(cname.getBytes("ISO-8859-1"),"UTF-8");
            System.out.println(cname);
            str.append(" and d1.departmentName like '%"+cname+"%' ");
        }
        String cpage=request.getParameter("page");
        String limit=request.getParameter("limit");
        //String cpage="1";
        //String limit="3";
        if(cpage!=null&&!cpage.equals("")){
            Page page=new Page();
            page.setCurrentPage(Integer.parseInt(cpage));
            page.setCount(Integer.parseInt(limit));
            List<Department> list=dao.getAllDepartmentByPage(str.toString(), page);
//            request.setAttribute("mds", list);
            int total=dao.getCount(str.toString());
            System.out.println(list);
            JSONObject jo=new JSONObject();
            jo.put("code", 0);
            jo.put("msg", "你好");
            jo.put("count",total );
            jo.put("data", list);
            System.out.println(jo.toJSONString());
            System.out.println(total);
            PrintWriter out=response.getWriter();
            out.print(jo.toString());
            out.flush();
            out.close();


        }
        //若没有分页
        else{
            //重定向（请求转发）
            request.getRequestDispatcher("/view/classes/DepartmentByPage.jsp").forward(request, response);
        }
    }
}
