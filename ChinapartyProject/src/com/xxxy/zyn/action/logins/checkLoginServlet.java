package com.xxxy.zyn.action.logins;

import com.xxxy.zyn.bean.Logins;
import com.xxxy.zyn.dao.LoginsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zyn
 * @date 2022-06-25-15:25
 */
@WebServlet("/checkLoginServlet")
public class checkLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html:charset=utf-8");
        String loginsName =req.getParameter("username");
        String loginsPwd =req.getParameter("password");
        Logins logins = new Logins();
        LoginsDao dao = new LoginsDao();
        logins.setLoginsName(loginsName);
        logins.setLoginsPwd(loginsPwd);
        String res = dao.checkLogin(logins);
        PrintWriter writer = resp.getWriter();
        writer.print(res);
        writer.flush();
        writer.close();
    }
}
