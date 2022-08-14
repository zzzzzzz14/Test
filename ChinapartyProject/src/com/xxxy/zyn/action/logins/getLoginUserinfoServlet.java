package com.xxxy.zyn.action.logins;

import com.alibaba.fastjson.JSONObject;
import com.xxxy.zyn.bean.Userinfo;
import com.xxxy.zyn.dao.LoginsDao;

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
 * @date 2022-06-08-9:25
 */
@WebServlet("/getLoginUserinfoServlet")
public class getLoginUserinfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        LoginsDao dao = new LoginsDao();

        List<Userinfo> list = dao.getUserinfo();
        JSONObject jo = new JSONObject();
        jo.put("data",list);
//        System.out.println(jo.toJSONString());
        PrintWriter out = response.getWriter();
        out.print(jo.toString());
        out.flush();
        out.close();
        request.setAttribute("Userinfo",list);
    }
}
