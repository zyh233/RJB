package cn.cumt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.cumt.entity.Record;
import cn.cumt.entity.Tag;
import cn.cumt.entity.User;
import cn.cumt.service.ImageService;
import cn.cumt.service.ImageServiceImpl;
import cn.cumt.service.RecordService;
import cn.cumt.service.RecordServiceImpl;
import cn.cumt.utils.AddTagUtil;
import cn.cumt.utils.DateFormat;
import cn.cumt.utils.TagsMergeUtil;
import cn.cumt.utils.TagsToString;

public class UpdateTag extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");	
		PrintWriter out = response.getWriter();
		String i = request.getParameter("id");
		int id = Integer.parseInt(i);
		String name = request.getParameter("name");	
		String tags = request.getParameter("tags");		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user!=null){
			int uid = user.getUid();
			Record record = new Record();
			record.setId(id);
			record.setUid(uid);
			record.setTags(tags);
			record.setName(name);
			RecordService rs = new RecordServiceImpl();
			String tag = rs.queryTag(uid, id);
			if(tag==null){
				rs.addRecord(record);
			}else {
				rs.updateRecord(record);
			}
			if(tag==null||TagsMergeUtil.singleMerge(tag, tags)<0.3){
				ImageService is = new ImageServiceImpl();
				List<Tag> tags1 = is.findTagsById(id);
				System.out.println(tags1.toString());
				List<Tag> list = AddTagUtil.addTag(tags, tags1);
				String str = TagsToString.tagsToString(list);
				System.out.println(str);
				is.updateImageTags(str, id, DateFormat.getDate());				
			}
			out.write("success");			
		}		
		out.flush();
		out.close();
	}	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
