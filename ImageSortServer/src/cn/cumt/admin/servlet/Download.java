package cn.cumt.admin.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.cumt.entity.Image;
import cn.cumt.utils.FileUtils;
import cn.cumt.utils.ImageTranslation;

public class Download extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		List<Image> images= (List<Image>) session.getAttribute("images");
		String translate = ImageTranslation.imageTranslate(images);
		File file = new File(getServletContext().getRealPath("/temp"),"tags.txt");
		FileUtils.String2File(file, translate);
		FileUtils.downloadFile(request, response, file);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
