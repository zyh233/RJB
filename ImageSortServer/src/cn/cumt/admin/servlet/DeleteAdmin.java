package cn.cumt.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cumt.service.AdminService;
import cn.cumt.service.AdminServiceImpl;

public class DeleteAdmin extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("aid");
		int aid = Integer.parseInt(id);
		AdminService service = new AdminServiceImpl();
		service.deleteAdmin(aid);
		response.sendRedirect(getServletContext().getContextPath()+"/admin/QueryAdmins");
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}

}
