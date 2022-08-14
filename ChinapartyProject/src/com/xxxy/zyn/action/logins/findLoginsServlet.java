package com.xxxy.zyn.action.logins;

import com.xxxy.zyn.bean.Logins;
import com.xxxy.zyn.dao.LoginsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zyn
 * @date 2022-06-08-8:42
 */
@WebServlet("/findLoginsServlet")
public class findLoginsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String cid = req.getParameter("logins_id");
        LoginsDao dao = new LoginsDao();
        Logins model = dao.findLoginsById(cid);
        req.setAttribute("model",model);
        req.getRequestDispatcher("/view/logins/LoginsUpdate.jsp").forward(req,resp);
    }
}
