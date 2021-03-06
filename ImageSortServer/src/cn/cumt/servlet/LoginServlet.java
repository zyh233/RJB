package cn.cumt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import cn.cumt.entity.User;
import cn.cumt.service.UserService;
import cn.cumt.service.UserServiceImpl;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	public static int count = 0;
	private static Object obj = new Object();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		UserService userService=new UserServiceImpl();
		User user=new User();
		HttpSession session = request.getSession();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			User user2=userService.Login(user);			
			if(user2!=null){				
				session.setAttribute("user", user2);
				Map<String,String> map = new HashMap<>();
				map.put("username",user2.getUsername());
				map.put("uid", user2.getUid()+"");				
				map.put("phone", user2.getPhone());
				map.put("email", user2.getEmail());
				map.put("hobbies", user2.getHobbies());
				map.put("score", user2.getScore()+"");
				JSONObject json=JSONObject.fromObject(map);
				JSONObject params=new JSONObject();
				params.put("user", json);
				out.write(params.toString());				
				synchronized (obj) {
					count++;
				}
			}
			else {
				out.write("wrong");
			} 
		} catch (IllegalAccessException e) {						
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		ServletContext application = getServletContext();
		application.setAttribute("count", count);
		System.out.println(count);
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
