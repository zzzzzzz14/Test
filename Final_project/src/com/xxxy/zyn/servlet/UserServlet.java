package com.xxxy.zyn.servlet;

import com.xxxy.zyn.bean.User;
import com.xxxy.zyn.dao.Userdao;
import com.xxxy.zyn.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author zyn
 * @date 2022-06-03-16:07
 */
public class UserServlet extends BaseServlet{
    /****
     * 添加user用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void adduser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        //获取session中的验证码
        String img = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//        获取之后马上删除
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("imgcode");
        if (img != null  && img.equalsIgnoreCase(code)) {
            User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
            Userdao dao = new Userdao();
            String findusername = dao.findusername(user);
            if(findusername=="用户名可用"){
                String str = dao.addUser(user);
                writer.print(str);
            }
            else {
                writer.print("用户名不可用");
            }
        }else{
            writer.print("验证码错误");
        }
    }

    /***
     * 登录login页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        //获取session中的验证码
        String img = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//        获取之后马上删除
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("imgcode");
        if (img != null  && img.equalsIgnoreCase(code)) {
            User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
            Userdao dao = new Userdao();
            String login = dao.login(user);
            if(login.equals("登录成功")){
                req.getSession().setAttribute("username",user.getUsername());
            }
            writer.print(login);
        }else{
            writer.print("验证码错误");
        }
    }

    /***
     * 退出登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        req.getSession().setAttribute("username",null);
//        req.getRequestDispatcher("/index.jsp").forward(req,resp);
//        采用页面重定向 不想url里有参数
        resp.sendRedirect("/Final_project/index.jsp");
    }

    /**
     * 根据用户名查找用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void finduser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        Userdao dao = new Userdao();
        Object str = req.getSession().getAttribute("username");
        String username= (String) str;
        User user = dao.findUser(username);
        req.setAttribute("user",user);
        req.getRequestDispatcher("/pages/user/user_control.jsp").forward(req,resp);
    }

    /**
     * 根据User对象修改用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateuser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        Userdao dao = new Userdao();
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        String str = dao.updateuser(user);
        PrintWriter writer = resp.getWriter();
        writer.print(str);
    }

    /**
     * 根据用户名查找用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findmoney(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        Userdao dao = new Userdao();
        Object str = req.getSession().getAttribute("username");
        String username= (String) str;
        User user = dao.findUser(username);
        req.setAttribute("user",user);
        req.getRequestDispatcher("/pages/user/user_skype.jsp").forward(req,resp);
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updatemoney(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        Userdao dao = new Userdao();
        Object s = req.getSession().getAttribute("username");
        String username= (String) s;
        String str = dao.updatemoney(Integer.parseInt(req.getParameter("money")), username);
        PrintWriter writer = resp.getWriter();
        writer.print(str);
    }
}
