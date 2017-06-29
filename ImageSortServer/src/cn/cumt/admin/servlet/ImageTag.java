package cn.cumt.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.cumt.entity.User2Image;
import cn.cumt.service.CascadeService;
import cn.cumt.service.RecordService;
import cn.cumt.service.RecordServiceImpl;

public class ImageTag extends HttpServlet {

	/**
	 * ∞¥Õº∆¨≤È’“
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		RecordService service = new RecordServiceImpl();
		int queryImageCount = service.queryImageCount();
		
		List<Integer> ids = service.queryIds();
		List<Integer> image2UserNum = service.image2UserNum();
		
		CascadeService cService = new CascadeService();
		List<User2Image> image2User = cService.image2User();
		
		
		HttpSession session = request.getSession();
		session.setAttribute("queryImageCount", queryImageCount);				
		JSONArray array = JSONArray.fromObject(ids);
		JSONArray array2 = JSONArray.fromObject(image2UserNum);
		session.setAttribute("array", array);
		session.setAttribute("array2", array2);
		session.setAttribute("image2User", image2User);
		
		
		
		response.sendRedirect(getServletContext().getContextPath()+"/admin/stats.jsp");
	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
