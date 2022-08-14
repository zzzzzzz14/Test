package com.xxxy.zyn.action.logins;

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
 * @date 2022-06-08-8:37
 */
@WebServlet("/deleteLoginsServlet")
public class deleteLoginsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html:charset=utf-8");
        //获取转发过来的id
        String logins_id=req.getParameter("logins_id");
        LoginsDao dao = new LoginsDao();
        String str = dao.deleteLogins(logins_id);
        PrintWriter writer = resp.getWriter();
        writer.print(str);
        writer.flush();
        writer.close();
    }
}
