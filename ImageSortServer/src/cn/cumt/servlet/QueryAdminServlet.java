package cn.cumt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import cn.cumt.entity.Admin;
import cn.cumt.service.AdminService;
import cn.cumt.service.AdminServiceImpl;

public class QueryAdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Admin login = (Admin) session.getAttribute("admin");
		if(login!=null){
			AdminService service = new AdminServiceImpl();
			List<Admin> admins = service.findAllAdmins();
			JSONArray array = JSONArray.fromObject(admins);
			out.write(array.toString());
		}
		else {
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
