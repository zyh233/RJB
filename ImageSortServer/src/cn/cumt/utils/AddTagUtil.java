package cn.cumt.utils;

import java.util.ArrayList;
import java.util.List;

import cn.cumt.entity.Tag;

public class AddTagUtil {
	/**
	 * 当标签数较少时，只要不是相等的标签，就添加
	 * @param s
	 * @param tags
	 * @return
	 */
	public static List<Tag> addTag(String s,List<Tag> tags){
		if(tags!=null){
			for (int i = 0; i < tags.size(); i++) {
				String string = tags.get(i).getTag();
				if(s.equals(string)||TagsMergeUtil.singleMerge(string, s)>0.3){
					int weights = tags.get(i).getWeights()+1;
					tags.get(i).setWeights(weights);
					return tags;
				}			
			}
		}else {
			tags = new ArrayList<>();
			Tag tag = new Tag();
			tag.setTag(s);
			tag.setWeights(1);
			tags.add(tag);
			return tags;
		}
		Tag tag = new Tag();
		tag.setTag(s);
		tag.setWeights(1);
		tags.add(tag);
		return tags;
		
	}

}
