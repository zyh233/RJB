package cn.cumt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.cumt.entity.User;
import cn.cumt.service.UserService;
import cn.cumt.service.UserServiceImpl;

public class UpdateScore extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");		
		String s = request.getParameter("score");
		int score = Integer.parseInt(s);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user!=null){
			int uid = user.getUid();
			UserService service = new UserServiceImpl();
			service.updateScore(uid, score);
		}		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
