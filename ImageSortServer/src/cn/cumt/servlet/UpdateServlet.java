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
import cn.cumt.exception.UpdateFailException;
import cn.cumt.service.UserService;
import cn.cumt.service.UserServiceImpl;

@SuppressWarnings("serial")
public class UpdateServlet extends HttpServlet {

	/**
	 * 用户更新类
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		UserService userService = new UserServiceImpl();
		boolean flag = false;
		User user=new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());	
			System.out.println(user.toString());
			flag = userService.updateUser(user);
		}catch (UpdateFailException e) {
				out.write(e.getMessage());
				e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		if(flag){
			out.write("success");
			response.sendRedirect(getServletContext().getContextPath()+"/admin/QueryUsers");
		}
		out.close();	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
