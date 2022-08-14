package com.xxxy.zyn.action.logins;

import com.alibaba.fastjson.JSONObject;
import com.xxxy.zyn.bean.Logins;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.dao.LoginsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author zyn
 * @date 2022-06-08-8:46
 */
@WebServlet("/getAllLoginsByPageServlet")
public class getAllLoginsByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        System.out.println("enter");
        LoginsDao dao = new LoginsDao();
//        查询获取参数
        String cflag=req.getParameter("cflag");
        String sdate=req.getParameter("sdate");
        String edate=req.getParameter("edate");
        String cname=req.getParameter("cname");

        StringBuffer str=new StringBuffer();
        if(cflag!=null&&!cflag.equals("")){
            if(cflag.equals("1")||cflag.equals("0")){
                str.append(" and loginsFlag="+cflag);
            }
        }
        if(sdate!=null&&!sdate.equals("")){
            str.append(" and loginsCDate>='"+sdate+"'");
        }
        if(edate!=null&&!edate.equals("")){
            str.append(" and loginsCDate<='"+edate+" 23:59:59'");
        }

        if(cname!=null&&!cname.equals("")){
//            byte[] bytes = cname.getBytes(StandardCharsets.ISO_8859_1);
//            //3.2 字节数组解码
//            cname = new String(bytes, StandardCharsets.UTF_8);
//            cname=new String(cname.getBytes("ISO-8859-1"),"UTF-8");
//            System.out.println(cname);
            str.append(" and loginsName like '%"+cname+"%' ");
        }
        String cpage=req.getParameter("page");
        String limit=req.getParameter("limit");
        if(cpage!=null&&!cpage.equals("")){
            Page page=new Page();
            page.setCurrentPage(Integer.parseInt(cpage));
            page.setCount(Integer.parseInt(limit));
            List<Logins> list=dao.getAllLoginsByPage(str.toString(), page);
//            request.setAttribute("mds", list);
            int total=dao.getCount(str.toString());
//            System.out.println(list);
            JSONObject jo=new JSONObject();
            jo.put("code", 0);
            jo.put("msg", "你好");
            jo.put("count",total );
            jo.put("data", list);
            System.out.println(jo.toJSONString());
//            System.out.println(total);
            PrintWriter out=resp.getWriter();
            out.print(jo.toString());
            out.flush();
            out.close();
        }
        //若没有分页
        else{
            //重定向（请求转发）
            req.getRequestDispatcher("/view/logins/LoginsByPage.jsp").forward(req, resp);
        }
    }
}
