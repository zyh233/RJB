package cn.cumt.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUtils {
	public static void String2File(File file,String s) throws IOException{
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(s.getBytes());
		fos.close();
	}
	
	public static void downloadFile(HttpServletRequest request, HttpServletResponse response,File file) throws IOException{
		
		response.setContentType(request.getServletContext().getMimeType(file.getName()));    
        response.setHeader("Content-Disposition", "attachment;filename="+file.getName());  
        String fullFileName = request.getServletContext().getRealPath("/temp/" + file.getName());  
        InputStream in = new FileInputStream(fullFileName);  
        OutputStream out = response.getOutputStream();          
        int b;  
        while((b=in.read())!= -1)  
        {  
            out.write(b);  
        }  
          
        in.close();  
        out.close();  
		
	}

}
