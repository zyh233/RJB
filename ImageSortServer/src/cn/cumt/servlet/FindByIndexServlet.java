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
import cn.cumt.utils.TagsToArray;

@SuppressWarnings("serial")
public class FindByIndexServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String index = request.getParameter("index");
		ImageService service = new ImageServiceImpl();
		List<Image> list = service.findByIndex(index);
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (Image image : list) {
			JSONObject jsonObject =new JSONObject();
			jsonObject.put("picture_name", image.getName());
			jsonObject.put( "finish_time", image.getDate());
			String tags = image.getTags();
			String[] strs = TagsToArray.strToArray(tags);
			jsonObject.put( "labels", strs);
			jsonArray.add(jsonObject);
		}
		json.put("images", jsonArray.toString());
		out.write(json.toString());
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
