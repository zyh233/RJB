package cn.cumt.servlet;

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

@SuppressWarnings("serial")
public class AdminAddServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Admin login = (Admin) session.getAttribute("admin");
		boolean flag = false;
		if(login!=null){			
			AdminService service = new AdminServiceImpl();
			Admin admin = new Admin();
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String isroot = request.getParameter("isroot");
			admin.setName(name);
			admin.setPassword(password);
			if(isroot.equals("1"))
				admin.setIsroot(true);
			else
				admin.setIsroot(false);
			flag = service.addAdmin(admin);
			System.out.println(admin);
		}
		if(flag)
			out.write("true");
		else 
			out.write("false");
		out.flush();
		out.close();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
