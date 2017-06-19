package cn.cumt.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputToStringUtil {
	public static String convertStreamToString(InputStream in) throws IOException{
		ByteArrayOutputStream   baos   =   new   ByteArrayOutputStream(); 
        int   i=-1; 
        while((i=in.read())!=-1){ 
        	baos.write(i); 
        }  
       return   baos.toString(); 
		
	}

}
