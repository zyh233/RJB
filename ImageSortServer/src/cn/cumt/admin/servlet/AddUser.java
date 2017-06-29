package cn.cumt.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.cumt.entity.User;
import cn.cumt.exception.UserRepeatException;
import cn.cumt.service.UserService;
import cn.cumt.service.UserServiceImpl;

public class AddUser extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");	
		PrintWriter out = response.getWriter();
		User user=new User();
		boolean flag=false;
		UserService userService = new UserServiceImpl();
		try {
			BeanUtils.populate(user, request.getParameterMap());			
			flag=userService.addUser(user);
		} catch (UserRepeatException e) {
			out.write(e.getMessage());
			e.printStackTrace();			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if(flag)			
			response.sendRedirect(getServletContext().getContextPath()+"/admin/QueryUsers");		 			
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
