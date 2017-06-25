package cn.cumt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import cn.cumt.entity.Record;
import cn.cumt.entity.User;
import cn.cumt.service.RecordService;
import cn.cumt.service.RecordServiceImpl;
import cn.cumt.utils.ReceiveUtil;

public class QueryReceiveTags extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		System.out.println("链接-------------->>>>>>>>>>>>>");
		if(user!=null){
			System.out.println("接受-------------->>>>>>>>>>>>>");
			int uid = user.getUid();
			RecordService service = new RecordServiceImpl();
			List<Record> records = service.findRecordsByUid(uid);			
			//查找标签是否预接受
			List<Integer> list = ReceiveUtil.QueryReceive(records);
			JSONArray array = JSONArray.fromObject(list);
			out.write(array.toString());
		}		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
		
	}

	

}
