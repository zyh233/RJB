package cn.cumt.admin.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.cumt.entity.User2Image;
import cn.cumt.service.CascadeService;
import cn.cumt.service.RecordService;
import cn.cumt.service.RecordServiceImpl;

public class UserTag extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		RecordService service = new RecordServiceImpl();
		int queryUserCount = service.queryUserCount();
		List<Integer> uids = service.queryUids();
		List<Integer> user2ImageNum = service.user2ImageNum();
		
		CascadeService cService = new CascadeService();
		List<User2Image> user2Image = cService.user2Image();
		
		HttpSession session = request.getSession();
		session.setAttribute("queryUserCount", queryUserCount);
		session.setAttribute("uids", uids);
		session.setAttribute("user2ImageNum", user2ImageNum);
		session.setAttribute("user2Image", user2Image);
		response.sendRedirect(getServletContext().getContextPath()+"/admin/user-stats.jsp");
		
	}
	
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
