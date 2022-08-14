package com.xxxy.zyn.servlet;

import com.alibaba.fastjson.JSONObject;
import com.xxxy.zyn.bean.Food;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.dao.Fooddao;
import com.xxxy.zyn.dao.Ordersdao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zyn
 * @date 2022-06-28-17:48
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet{

    protected void addorder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        Fooddao dao = new Fooddao();
        String fname = req.getParameter("fname");
        Food food = dao.FindFood(fname);
        Ordersdao ordersdao = new Ordersdao();
        Object username = req.getSession().getAttribute("username");
        String str = ordersdao.addorders(food, (String) username);
        PrintWriter writer = resp.getWriter();
        writer.print(str);
    }

    /**
     * select我的订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void selectMyorders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        String cpage = req.getParameter("page");
        String limit = req.getParameter("limit");
        if(cpage!=null&&!cpage.equals("")){
            Page page = new Page();
            page.setCurrentPage(Integer.parseInt(cpage));
            page.setCount(Integer.parseInt(limit));
            Ordersdao dao = new Ordersdao();
            Object username = req.getSession().getAttribute("username");
            JSONObject jsonObject = dao.Page(page, (String) username);
            PrintWriter out = resp.getWriter();
            out.print(jsonObject);
//            System.out.println(jsonObject);
        }
    }
    protected void increase(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Ordersdao dao = new Ordersdao();
        int flag = dao.increase(id);
//        异步接受成功时，需要servlet输出值；
        PrintWriter out = resp.getWriter();
        out.print(flag);//jsp页面接受的值；
    }
    protected void reduce(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Ordersdao dao = new Ordersdao();
        PrintWriter out = resp.getWriter();
        out.print(dao.reduce(id));
    }
    protected void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Ordersdao dao = new Ordersdao();
        PrintWriter out = resp.getWriter();
        out.print(dao.delete(id));
    }

    /**
     * 结算订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void balance(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        Object username = req.getSession().getAttribute("username");
        Ordersdao dao = new Ordersdao();
        String str = dao.balance((String) username);
        PrintWriter writer = resp.getWriter();
        writer.print(str);
        req.getSession().setAttribute("flag",1);
    }

    /***
     * 将订单添加到历史订单中
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void orderhistory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        Object username = req.getSession().getAttribute("username");
        Ordersdao dao = new Ordersdao();
        String str = dao.orderhistory((String) username);
        PrintWriter writer = resp.getWriter();
    }
    /**
     * select我的历史订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void selectMyHisorders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        String cpage = req.getParameter("page");
        String limit = req.getParameter("limit");
        if(cpage!=null&&!cpage.equals("")){
            Page page = new Page();
            page.setCurrentPage(Integer.parseInt(cpage));
            page.setCount(Integer.parseInt(limit));
            Ordersdao dao = new Ordersdao();
            Object username = req.getSession().getAttribute("username");
            JSONObject jsonObject = dao.Pagehistory(page, (String) username);
            PrintWriter out = resp.getWriter();
            out.print(jsonObject);
        }
    }
}
