package com.zyn.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zyn.bean.Page;
import com.zyn.dao.getJson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zyn
 * @date 2022-05-18-15:41
 */
public class Json extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        getJson getJson = new getJson();
//        JSONObject jsonObject = getJson.select_();
////        JsonObject jsonObject1 = getJson.select();
//        out.print(jsonObject);
//
//    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        getJson getJson = new getJson();
        String cpage = req.getParameter("page");
        String limit = req.getParameter("limit");
        if(cpage!=null&&!cpage.equals("")){
            Page page = new Page();
            page.setCurrentPage(Integer.parseInt(cpage));
            page.setCount(Integer.parseInt(limit));
            JSONObject jsonObject = getJson.Page(page);
            PrintWriter out = resp.getWriter();
            out.print(jsonObject);
//            System.out.println(jsonObject);
        }
    }
}
