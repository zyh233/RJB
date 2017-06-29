package cn.cumt.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.cumt.entity.Admin;
import cn.cumt.service.AdminService;
import cn.cumt.service.AdminServiceImpl;

public class AddAdmin extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");				
		boolean flag = false;					
		AdminService service = new AdminServiceImpl();
		Admin admin = new Admin();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String isroot = request.getParameter("isroot");
		System.out.println(isroot);
		admin.setName(name);
		admin.setPassword(password);
		if(isroot==null)
			admin.setIsroot(false);
		else if (isroot.equals("1"))
			admin.setIsroot(true);	
		flag = service.addAdmin(admin);		
		if(flag)
			response.sendRedirect(getServletContext().getContextPath()+"/admin/QueryAdmins");										
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
