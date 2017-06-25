package cn.cumt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import cn.cumt.entity.Record;
import cn.cumt.service.RecordService;
import cn.cumt.service.RecordServiceImpl;

public class QueryRecords extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		RecordService service = new RecordServiceImpl();
		String u = request.getParameter("uid");
		int uid = Integer.parseInt(u);
		List<Record> records = service.queryRecordById(uid);
		if (records.size()!=0) {
			JSONArray array = JSONArray.fromObject(records);
			out.write(array.toString());
		}else {
			out.write("worng");
		}
		out.flush();
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

}
