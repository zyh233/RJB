package cn.cumt.utils;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.cumt.entity.Image;

public class ImageTranslation {
	
	public static String imageTranslate(List<Image> images){
		
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (Image image : images) {
			JSONObject jsonObject =new JSONObject();
			jsonObject.put("picture_name", image.getName());
			jsonObject.put( "finish_time", image.getDate());
			String tags = image.getTags();
			String[] strs = TagsToArray.strToArray(tags);
			jsonObject.put( "labels", strs);
			jsonArray.add(jsonObject);
		}
		json.put("images", jsonArray.toString());
		
		return json.toString();
	}

}
