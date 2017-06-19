package cn.cumt.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.cumt.service.AdminService;
import cn.cumt.service.AdminServiceImpl;
import cn.cumt.service.ImageService;
import cn.cumt.service.ImageServiceImpl;
import cn.cumt.service.UserService;
import cn.cumt.service.UserServiceImpl;

public class InitIndex extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		UserService uService = new UserServiceImpl();
		int userCount = uService.userCount();
		AdminService aService = new AdminServiceImpl();
		int adminCount = aService.adminCount();
		ImageService iService = new ImageServiceImpl();
		int imageCount = iService.imageCount();
		HttpSession session = request.getSession();
		session.setAttribute("userCount", userCount);
		session.setAttribute("adminCount", adminCount);
		session.setAttribute("imageCount", imageCount);
		response.sendRedirect(getServletContext().getContextPath()+"/admin/index.jsp");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
