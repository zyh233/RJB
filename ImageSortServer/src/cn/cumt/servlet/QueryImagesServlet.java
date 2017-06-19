package cn.cumt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.cumt.entity.Admin;
import cn.cumt.entity.Image;
import cn.cumt.service.ImageService;
import cn.cumt.service.ImageServiceImpl;
import cn.cumt.utils.ImageTranslation;

@SuppressWarnings("serial")
public class QueryImagesServlet extends HttpServlet {

	/**
	 * 
	 * 图片导出标签的处理类
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//按索引查找
		//String index = request.getParameter("index");
		HttpSession session = request.getSession();
		Admin login = (Admin) session.getAttribute("admin");
		if(login!=null){
			ImageService service = new ImageServiceImpl();
			List<Image> images = service.exportImages();
			String translate = ImageTranslation.imageTranslate(images);
			out.write(translate);
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
