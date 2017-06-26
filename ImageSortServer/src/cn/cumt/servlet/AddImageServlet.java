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
		        facotry.setRepository(new File(tempDir));//������ʱ�ļ����Ŀ¼     
		        
		        facotry.setSizeThreshold(4*1024);//���û���
		        
		        // 2���������Ľ����ࣺServletFileUpload
		        ServletFileUpload upload = new ServletFileUpload(facotry);  
		        
		        upload.setHeaderEncoding("UTF-8");// ����ϴ����ļ�������  
		        
		        upload.setFileSizeMax(1024 * 1024 * 1024);// �����ļ��ϴ����ֵ��1M  
		        upload.setSizeMax(2048 * 1024 * 1024);//�ļ��ϴ����ܴ�С����  
		
		        // 3���ж��û��ı��ύ��ʽ�ǲ���multipart/form-data  
		        boolean bb = upload.isMultipartContent(request);  
		        if (!bb) { 		        	
		            return;  
		        }  
		        // 4���ǣ�����request�������������List<FileItem>  
		        List<FileItem> items = upload.parseRequest(request); 
		        
		        String storePath = getServletContext().getRealPath(  
		                "/images");// �ϴ����ļ��Ĵ��Ŀ¼  
		        for (FileItem item : items)
		        {  
		            if (item.isFormField()) 
		            {  
		                // 5���ж��Ƿ�����ͨ������ӡ����  
		                String fieldName = item.getFieldName();// ���������  
		                String fieldValue = item.getString("UTF-8");// �������ֵ  
		                System.out.println(fieldName + "=" + fieldValue);  
		            } else 
		            {  
		                // 6���ϴ������õ��������������ϴ������浽��������ĳ��Ŀ¼�У�����ʱ���ļ�����ɶ��  
		                String fileName = item.getName();// �õ��ϴ��ļ������� C:\Documents  
		                                                    // and  
		                                                    // Settings\shc\����\a.txt  
		                                                    // a.txt  
		                //����û�û��ѡ���ļ��ϴ������  
		                if(fileName==null||fileName.trim().equals(""))
		                {  
		                    continue;  
		                }  
		                fileName = fileName  
		                        .substring(fileName.lastIndexOf("\\") + 1);  		                
		                System.out.println("�ϴ����ļ����ǣ�" + fileName);  
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
		                item.delete();//ɾ����ʱ�ļ�  
		            }  
		       }
		       int count = service.addImages(list);
		      out1.write("success:"+count);
		      request.getRequestDispatcher("/admin/uploadSuccess.jsp").forward(request, response);
        }catch(FileUploadBase.FileSizeLimitExceededException e){  
        	
	        request.setAttribute("message", "�����ļ���С���ܳ���5M");  
        }catch(FileUploadBase.SizeLimitExceededException e){  
	        request.setAttribute("message", "���ļ���С���ܳ���7M");   
        }catch (Exception e) {  
	        e.printStackTrace();  
	        request.setAttribute("message", "�ϴ�ʧ��");  
        }  
}  


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
