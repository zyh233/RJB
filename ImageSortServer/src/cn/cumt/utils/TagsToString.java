package cn.cumt.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.cumt.entity.Tag;

public class TagsToString {
	/**
	 * 把Set集合中的标签转换成字符串
	 * @param set
	 * @return
	 * 
	 */
	public static String tagsToString(Set<Tag> set){
		Iterator<Tag> it = set.iterator();
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			Tag tag = it.next();
			sb.append(tag.toString());
			sb.append(",");
		}
		String str = sb.toString();
		return str.substring(0, str.length()-1);		
	}
	
	public static String tagsToString(List<Tag> list){
		StringBuffer sb = new StringBuffer();
		for (Tag tag : list) {
			sb.append(tag.toString());
			sb.append(",");
		}
		String str = sb.toString();
		return str.substring(0, str.length()-1);
		
	}

}
