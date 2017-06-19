package cn.cumt.servlet;

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

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {

	/**
	 * 用户注册处理类
	 * @author 永红
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
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
			System.out.println(user.toString());
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
			out.write("success");
		out.close();
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
