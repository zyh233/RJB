package cn.cumt.utils;

import java.util.ArrayList;
import java.util.List;

import cn.cumt.entity.Image;

public class RecommendImage {
	
	public static List<Image>  recommendImage(String hobbies,List<Image> images){
		List<Image> list =new ArrayList<>();
		String[] hobby = hobbies.split(",");
		boolean flag = false;
		int start = (int)(Math.random()*images.size());
		int end = (start+20)<images.size()?(start+20):images.size();
		for(int i = start;i<end;i++){
			flag = false;
			String tags = images.get(i).getTags();
			if(tags!=null){
				String[] tag = TagsToArray.strToArray(tags);
				for(int j =0;j<hobby.length;j++){
					for (int k = 0; k < tag.length; k++) {
						double result = TagsMergeUtil.singleMerge(hobby[j], tag[k]);
						if(result>0.4){
							list.add(images.get(i));
							flag = true;
							break;
						}
					}
					if(flag){
						break;
					}					
				}
			}				
		}
		return list;
		
	}

}
