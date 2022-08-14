package com.xxxy.zyn.servlet;

import com.alibaba.fastjson.JSONObject;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.bean.Rate;
import com.xxxy.zyn.dao.Ratedao;
import com.xxxy.zyn.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author zyn
 * @date 2022-06-29-10:16
 */
@WebServlet("/RateServlet")
public class RateServlet extends BaseServlet{

    /***
     * 添加评价
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addrate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        Object flag = req.getSession().getAttribute("flag");
        if(flag==null){
            writer.print("erro");
        }else{
            Object username = req.getSession().getAttribute("username");
            Rate rate = WebUtils.copyParamToBean(req.getParameterMap(), new Rate());
            rate.setUsername((String) username);
            Date date = new Date();
            rate.setRatedate(date);
            Ratedao dao = new Ratedao();
            String str = dao.addrate(rate);
            writer.print(str);
            req.getSession().setAttribute("flag",null);
        }
    }

    /**
     * 查询我的评论
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void selectMyrate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        String cpage = req.getParameter("page");
        String limit = req.getParameter("limit");
        if (cpage != null && !cpage.equals("")) {
            Page page = new Page();
            page.setCurrentPage(Integer.parseInt(cpage));
            page.setCount(Integer.parseInt(limit));
            Ratedao dao = new Ratedao();
            Object username = req.getSession().getAttribute("username");
            JSONObject jsonObject = dao.Page(page, (String) username);
            PrintWriter out = resp.getWriter();
            out.print(jsonObject);
//            System.out.println(jsonObject);
        }
    }

    /***
     * 删除评价
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delrate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        Ratedao dao = new Ratedao();
        String str = dao.delete(req.getParameter("id"));
        writer.print(str);
    }

    /**
     * 查询热门评论
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void selectHotrate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        String cpage = req.getParameter("page");
        String limit = req.getParameter("limit");
        if (cpage != null && !cpage.equals("")) {
            Page page = new Page();
            page.setCurrentPage(Integer.parseInt(cpage));
            page.setCount(Integer.parseInt(limit));
            Ratedao dao = new Ratedao();
            JSONObject jsonObject = dao.Pagehot(page);
            PrintWriter out = resp.getWriter();
            out.print(jsonObject);
//            System.out.println(jsonObject);
        }
    }
}
