package cn.cumt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.cumt.entity.Image;
import cn.cumt.service.ImageService;
import cn.cumt.service.ImageServiceImpl;
import cn.cumt.service.UserService;
import cn.cumt.service.UserServiceImpl;
import cn.cumt.utils.RecommendImage;

@SuppressWarnings("serial")
public class ImageDistributeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();		
		ImageService imageService = new ImageServiceImpl();
		UserService service = new UserServiceImpl();
		String uid = request.getParameter("uid");
		String hobbies = service.getHobbies(Integer.parseInt(uid));		
		List<Image> images = imageService.findAllImage();		
		JSONObject json = null;
		JSONArray jsons = new JSONArray();
		JSONObject parm = new JSONObject();
		int count = 3;
		if(hobbies==null){
			while (count>0) 
			{
				int index = (int)(Math.random()*images.size());
				json=new JSONObject();
				Image image = images.get(index);
				json.put("id", image.getId());
				json.put("name", image.getName());
				json.put("tags", image.getTags());
				json.put("url", image.getUrl());
				jsons.add(json);
				count--;
			}
		}else {
			List<Image> ris = RecommendImage.recommendImage(hobbies, images);
			if(ris.size()<count){
				int ic = count-ris.size();
				while(ic>0){
					int index = (int)(Math.random()*images.size());
					ris.add(images.get(index));
					ic--;
				}
			}
			for (Image image : ris) {
				json = new JSONObject();
				json.put("id", image.getId());
				json.put("name", image.getName());
				json.put("tags", image.getTags());
				json.put("url", image.getUrl());
				jsons.add(json);				
			}			
		}
		parm.put("images", jsons);
		out.write(parm.toString());
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
