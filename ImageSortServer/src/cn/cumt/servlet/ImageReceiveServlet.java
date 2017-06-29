package cn.cumt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

import cn.cumt.entity.Record;
import cn.cumt.entity.Tag;
import cn.cumt.exception.InsertRecordException;
import cn.cumt.service.ImageService;
import cn.cumt.service.ImageServiceImpl;
import cn.cumt.service.RecordService;
import cn.cumt.service.RecordServiceImpl;
import cn.cumt.service.UserService;
import cn.cumt.service.UserServiceImpl;
import cn.cumt.utils.AddTagUtil;
import cn.cumt.utils.DateFormat;
import cn.cumt.utils.HobbiesUtils;
import cn.cumt.utils.TagsMergeUtil;
import cn.cumt.utils.TagsToString;

@SuppressWarnings("serial")
public class ImageReceiveServlet extends HttpServlet {

	/**
	 * 接收图片的处理器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		RecordService rs = new RecordServiceImpl();
		ImageService is = new ImageServiceImpl();
		UserService us = new UserServiceImpl();
		Record record = new Record();				
		try {
			BeanUtils.populate(record, request.getParameterMap());	
			List<Record> records = null;
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("records");
			if(obj!=null){
				records=(List<Record>) obj;
			}else {
				records = new ArrayList<>();
			}
			records.add(record);
			session.setAttribute("records", records);
			records = (List<Record>) session.getAttribute("records");
			//-----------------------------------------------------------
			//添加多条记录
			if(records.size()>=3)
				rs.addRecords(records);		
			//更新用户爱好
			int uid = record.getUid();
			String s = record.getTags();
			String hobbies = us.getHobbies(uid);
			if(hobbies==null){
				us.setHobbies(uid, s);
			}else {
				String hobby = HobbiesUtils.addHobbies(s, hobbies);
				us.setHobbies(uid, hobby);
			}
			//更新图片标签
			int id = record.getId();
			List<Tag> tags = is.findTagsById(id);
			if(tags==null||tags.size()<4){
				List<Tag> list = AddTagUtil.addTag(s, tags);
				String str = TagsToString.tagsToString(list);
				is.updateImageTags(str, id, DateFormat.getDate());
			}else {
				List<Tag> list = TagsMergeUtil.stringMerge(s, tags);
				String str = TagsToString.tagsToString(list);
				is.updateImageTags(str, id, DateFormat.getDate());
			}			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InsertRecordException e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}


	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
