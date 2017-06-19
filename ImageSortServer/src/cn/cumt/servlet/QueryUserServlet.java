package cn.cumt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.cumt.entity.Admin;
import cn.cumt.entity.User;
import cn.cumt.service.AdminService;
import cn.cumt.service.AdminServiceImpl;
import cn.cumt.service.UserService;
import cn.cumt.service.UserServiceImpl;

@SuppressWarnings("serial")
public class QueryUserServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Admin login = (Admin) session.getAttribute("admin");
		if(login!=null)
		{
			UserService service = new UserServiceImpl();
			List<User> users = service.findAll();
			JSONObject json = null;
			JSONArray jsons = new JSONArray();
			JSONObject parm = new JSONObject();
			for (User user : users) 
			{
				json=new JSONObject();
				json.put("uid", user.getUid());
				json.put("username", user.getUsername());
				json.put("password", user.getPassword());
				json.put("phone", user.getPhone());
				json.put("email", user.getEmail());
				json.put("hobbies", user.getHobbies());
				jsons.add(json);
			}
			parm.put("users", jsons);
			out.write(parm.toString());
		}else {
			out.write("È¨ÏÞ²»×ã");
		}		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
