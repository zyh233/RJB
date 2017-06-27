package cn.cumt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.cumt.entity.User;
import cn.cumt.service.UserService;
import cn.cumt.service.UserServiceImpl;

public class UpdateUser extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		User user1 = new User();
		boolean upadte = false;
		try {
			BeanUtils.populate(user1, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//管理端修改用户信息
		if(user1.getUid()!=0){
			UserService service = new UserServiceImpl();
			upadte = service.upadte(user1);
		}
		else {
			//用户端修改用户信息
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");		
			if(user!=null){
				if(user1.getPassword()==null){
					user1.setPassword(user.getPassword());
				}
				int uid = user.getUid();
				user1.setUid(uid);
				System.out.println(user1);
				UserService service = new UserServiceImpl();
				upadte = service.upadte(user1);			
			}
		}
		
		if(upadte){
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
