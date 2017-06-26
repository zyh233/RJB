package cn.cumt.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.cumt.entity.Image;
import cn.cumt.service.ImageService;
import cn.cumt.service.ImageServiceImpl;

@SuppressWarnings("serial")
public class AddImageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ImageService service = new ImageServiceImpl();
		List<Image> list = new ArrayList<>();
		Image image = null;
		PrintWriter out1 = response.getWriter();
		try {
		        DiskFileItemFactory facotry = new DiskFileItemFactory();  
		        
		        String tempDir = getServletContext().getRealPath("/temp");  
		        facotry.setRepository(new File(tempDir));//设置临时文件存放目录     
		        
		        facotry.setSizeThreshold(4*1024);//设置缓存
		        
		        // 2、创建核心解析类：ServletFileUpload
		        ServletFileUpload upload = new ServletFileUpload(facotry);  
		        
		        upload.setHeaderEncoding("UTF-8");// 解决上传的文件名乱码  
		        
		        upload.setFileSizeMax(1024 * 1024 * 1024);// 单个文件上传最大值是1M  
		        upload.setSizeMax(2048 * 1024 * 1024);//文件上传的总大小限制  
		
		        // 3、判断用户的表单提交方式是不是multipart/form-data  
		        boolean bb = upload.isMultipartContent(request);  
		        if (!bb) { 		        	
		            return;  
		        }  
		        // 4、是：解析request对象的正文内容List<FileItem>  
		        List<FileItem> items = upload.parseRequest(request); 
		        
		        String storePath = getServletContext().getRealPath(  
		                "/images");// 上传的文件的存放目录  
		        for (FileItem item : items)
		        {  
		            if (item.isFormField()) 
		            {  
		                // 5、判断是否是普通表单：打印看看  
		                String fieldName = item.getFieldName();// 请求参数名  
		                String fieldValue = item.getString("UTF-8");// 请求参数值  
		                System.out.println(fieldName + "=" + fieldValue);  
		            } else 
		            {  
		                // 6、上传表单：得到输入流，处理上传：保存到服务器的某个目录中，保存时的文件名是啥？  
		                String fileName = item.getName();// 得到上传文件的名称 C:\Documents  
		                                                    // and  
		                                                    // Settings\shc\桌面\a.txt  
		                                                    // a.txt  
		                //解决用户没有选择文件上传的情况  
		                if(fileName==null||fileName.trim().equals(""))
		                {  
		                    continue;  
		                }  
		                fileName = fileName  
		                        .substring(fileName.lastIndexOf("\\") + 1);  		                
		                System.out.println("上传的文件名是：" + fileName);  
		                InputStream in = item.getInputStream();  
		                File file = new File(storePath,fileName);
		                OutputStream out = new FileOutputStream(file);  
		                byte b[] = new byte[1024];  
		                int len = 0;  
		                while ((len = in.read(b)) != -1) 
		                {  
		                    out.write(b, 0, len);  
		                }  
		                in.close();  
		                out.close(); 
		                image = new Image();
		                image.setName(fileName);
		                list.add(image);
		                item.delete();//删除临时文件  
		            }  
		       }
		       int count = service.addImages(list);
		      out1.write("success:"+count);
		      request.getRequestDispatcher("/admin/uploadSuccess.jsp").forward(request, response);
        }catch(FileUploadBase.FileSizeLimitExceededException e){  
        	
	        request.setAttribute("message", "单个文件大小不能超出5M");  
        }catch(FileUploadBase.SizeLimitExceededException e){  
	        request.setAttribute("message", "总文件大小不能超出7M");   
        }catch (Exception e) {  
	        e.printStackTrace();  
	        request.setAttribute("message", "上传失败");  
        }  
}  


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
