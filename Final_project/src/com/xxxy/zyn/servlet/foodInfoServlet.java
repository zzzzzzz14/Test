package com.xxxy.zyn.servlet;

import com.xxxy.zyn.bean.Food;
import com.xxxy.zyn.dao.Fooddao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zyn
 * @date 2022-06-28-10:46
 */
@WebServlet("/foodInfoServlet")
public class foodInfoServlet extends BaseServlet{

    protected void findFood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        Fooddao dao = new Fooddao();
        String fname = req.getParameter("fname");

//        解决隐藏域传来的值的中文乱码
        byte [] bytes = fname .getBytes("ISO-8859-1");
        fname = new String(bytes, "utf-8");

        Food food = dao.FindFood(fname);
        req.setAttribute("food",food);
        req.getRequestDispatcher("/pages/menu/info.jsp").forward(req,resp);
    }
}
