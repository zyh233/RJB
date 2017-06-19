package cn.cumt.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.cumt.entity.Image;
import cn.cumt.service.ImageService;
import cn.cumt.service.ImageServiceImpl;

@SuppressWarnings("serial")
public class ExportTags extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ImageService service = new ImageServiceImpl();
		List<Image> images = null;
		String index = request.getParameter("index");
		if(index!=""){
			images = service.findByIndex(index);
		}else {
			images = service.exportImages();
		}
		HttpSession session = request.getSession();
		session.setAttribute("images", images);
		response.sendRedirect(getServletContext().getContextPath()+"/admin/exportTags.jsp");			
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
