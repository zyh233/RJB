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

public class AdminUpdateServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Admin login = (Admin) session.getAttribute("admin");
		boolean update = false;
		if(login!=null){
			Admin admin = new Admin();
			String aid = request.getParameter("aid");		
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			admin.setAid(Integer.parseInt(aid));
			admin.setName(name);
			admin.setPassword(password);
			System.out.println(admin.toString());
			AdminService service = new AdminServiceImpl();
			update = service.updateAdmin(admin);
		}		
		if(update){
			out.write("success");
		}else {
			out.write("failure");
		}
		out.flush();
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
