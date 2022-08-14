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
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author zyn
 * @date 2022-06-08-8:53
 */
@WebServlet("/updateLoginsServlet")
public class updateLoginsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html:charset=utf-8");

        String userinfo_id =req.getParameter("userinfo_id");
        String roles_id =req.getParameter("roles_id");
        String loginsName =req.getParameter("loginsName");
        String loginsPwd =req.getParameter("loginsPwd");
        String loginsFlag =req.getParameter("loginsFlag");
        String loginsCDate =req.getParameter("loginsCDate");
        String logins_id =req.getParameter("logins_id");

        Logins model = new Logins();
        model.setLogins_id(logins_id);
        model.setUserinfo_id(userinfo_id);
        model.setRoles_id(roles_id);
        model.setLoginsName(loginsName);
        model.setLoginsPwd(loginsPwd);
        model.setLoginsFlag(loginsFlag);
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            model.setLoginsCDate(f.parse(loginsCDate));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LoginsDao dao = new LoginsDao();
        PrintWriter writer = resp.getWriter();
        String str = dao.updateLogins(model);
        writer.print(str);
        writer.flush();
        writer.close();
    }
}
