package com.xxxy.zyn.action.branch.theme;

import com.xxxy.zyn.bean.theme;
import com.xxxy.zyn.dao.ThemeDao;

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
 * Servlet implementation class updateThemeServlet
 */
@WebServlet("/updateThemeServlet")
public class updateThemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateThemeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
	
	    String ctitle=request.getParameter("ctitle");
	    String cflag=request.getParameter("cflag");
	    String cdate=request.getParameter("cdate");
	    String cuuid=request.getParameter("cid");

	    theme model=new theme();
	    model.setTheme_id(cuuid);
	    model.setThemeTitle(ctitle);
	    model.setThemeFlag(cflag);
	    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
			model.setThemeCDate(f.parse(cdate));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
        ThemeDao dao= new ThemeDao();
        String str=dao.updateTheme(model);
        System.out.println(str);
        PrintWriter out=response.getWriter();
        out.write(str);
	    out.flush();
	    out.close();
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}