package cn.cumt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	public static String getDate(){
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy.MM.dd'T'HH:mm:ss'Z'");
		String date = format.format(new Date());
		return date;
	}

}
