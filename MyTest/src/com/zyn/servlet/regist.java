package com.zyn.servlet; /**
 * @author zyn
 * @date 2022-05-14-17:22
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(name = "regist", value = "/regist")
public class regist extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        System.out.println("enter");
        //获取session中的验证码
        String img = (String)request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//        获取之后马上删除
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = request.getParameter("img");
        if (img != null  && img.equalsIgnoreCase(code)) {
            response.getWriter().write("success");
        }else{
            response.getWriter().write("defild");
        }
    }
}
