package cn.cumt.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.cumt.entity.User;
import cn.cumt.service.UserService;
import cn.cumt.service.UserServiceImpl;

public class QueryUsers extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		UserService service = new UserServiceImpl();
		List<User> users = service.findAll();
		HttpSession session = request.getSession();
		session.setAttribute("users", users);
		//request.setAttribute("users", users);
		request.getRequestDispatcher("/admin/users.jsp").forward(request, response);   
		out.flush();
		out.close();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
