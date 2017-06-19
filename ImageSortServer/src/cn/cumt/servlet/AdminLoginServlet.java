package cn.cumt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import cn.cumt.entity.Admin;
import cn.cumt.service.AdminService;
import cn.cumt.service.AdminServiceImpl;

@SuppressWarnings("serial")
public class AdminLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		AdminService service = new AdminServiceImpl();
		Admin admin = new Admin();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		admin.setName(name);
		admin.setPassword(password);
		Admin login = service.login(admin);
		if(login!=null){
			JSONObject param = new JSONObject();
			param.put("aid", login.getAid());
			param.put("name", login.getName());
			param.put("isroot", login.isIsroot());
			JSONObject json = new JSONObject();
			json.put("admin", param);
			out.write(json.toString());
			
			HttpSession session = request.getSession();
			session.setAttribute("admin", login);
			
		}else {
			out.write("wrong");
		}
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
