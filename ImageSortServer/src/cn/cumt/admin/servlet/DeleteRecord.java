package cn.cumt.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cumt.service.RecordService;
import cn.cumt.service.RecordServiceImpl;

public class DeleteRecord extends HttpServlet {

	/**
		É¾³ý¼ÇÂ¼
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String uid = request.getParameter("uid");
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		int u = Integer.parseInt(uid);
		int i = Integer.parseInt(id);
		RecordService service = new RecordServiceImpl();
		service.deleteRecord(u, i);
		if(flag.equals("1"))
			request.getRequestDispatcher("/admin/UserTag").forward(request,response);
		else {
			request.getRequestDispatcher("/admin/ImageTag").forward(request,response);
		}
				
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
