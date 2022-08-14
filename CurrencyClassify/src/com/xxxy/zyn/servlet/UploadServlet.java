package com.xxxy.zyn.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
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
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //缓存临时文件
        String tempPath = this.getServletContext().getRealPath("WEB-INF/temp");
        File tempFile = new File(tempPath);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        factory.setRepository(tempFile);
        ServletFileUpload fileupload = new ServletFileUpload(factory);
        fileupload.setHeaderEncoding("utf-8");
        try {
            List<FileItem> fileitems = fileupload.parseRequest(request);
            PrintWriter out = response.getWriter();
            //遍历表单标签集合fileitems
            for (FileItem fitem : fileitems) {
                if (fitem.isFormField()) {//是否为普通字段true
                    String name = fitem.getFieldName();
                    String value = fitem.getString("utf-8");
//					out.write(name+":"+value+"<br />");
                } else {//文件字段
                    String fname = fitem.getName();
                    if (fname != null && !fname.equals("")) {
//                        out.write("上传的文件名是：" + fname + "<br />");
                        fname = fname.substring(fname.lastIndexOf("\\") + 1);
                        fname = UUID.randomUUID().toString() + "_" + fname;
                        String webPath = "/upload/";
                        String realPath = this.getServletContext().getRealPath(webPath + fname);
                        System.out.println(realPath);
                        File file = new File(realPath);
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                        InputStream fin = fitem.getInputStream();
                        FileOutputStream fout = new FileOutputStream(file);
                        byte buffer[] = new byte[1024];
                        int len;
                        while ((len = fin.read(buffer)) > 0) {
                            fout.write(buffer, 0, len);
                        }
                        fin.close();
                        fout.close();
                        fitem.delete();
//                        out.write("上传文件成功！<br />");
                        HashMap map = new HashMap<>();
                        map.put("src",realPath);
                        JSONObject jo = new JSONObject();
                        jo.put("code",0);
                        jo.put("msg","你好");
                        jo.put("data",map);
                        System.out.println(jo.toString());
                        out.write(jo.toString());
                        out.flush();
                        out.close();
                    }
                }
            }
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
