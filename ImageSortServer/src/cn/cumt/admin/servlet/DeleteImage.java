package cn.cumt.admin.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cumt.service.ImageService;
import cn.cumt.service.ImageServiceImpl;

public class DeleteImage extends HttpServlet {	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		int i = Integer.parseInt(id);
		ImageService service = new ImageServiceImpl();
		String fileName = service.findName(i);
		service.deleteImage(i);
		File file = new File(getServletContext().getRealPath("/images"),fileName);
		if(file.exists()){
			file.delete();
		}
		response.sendRedirect(getServletContext().getContextPath()+"/admin/QueryImages");
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
