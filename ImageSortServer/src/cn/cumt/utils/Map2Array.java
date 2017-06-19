package cn.cumt.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Map2Array {
	
	public void map2Array(HttpServletRequest request,Map<Integer, Integer> map){
		List<Integer> uids = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {  
			  
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
		  
		}  
		
	}

}
