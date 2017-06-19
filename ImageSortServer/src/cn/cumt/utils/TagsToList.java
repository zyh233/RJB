package cn.cumt.utils;

import java.util.ArrayList;
import java.util.List;

import cn.cumt.entity.Tag;

public class TagsToList {
	/**
	 * 把从数据库读出来的字符串标签转换成List集合标签
	 * @param s
	 * @return
	 */
	public static List<Tag> stringToTags(String s){
		if(s==null){
			return null;
		}
		List<Tag> list = new ArrayList<>();
		Tag tag = null;
		String[] split = s.split(",");
		for (String string : split) {
			String[] tags = string.split(":");
			tag = new Tag(tags[0], Integer.valueOf(tags[1]));
			list.add(tag);
		}
		return list;
		
	}
}
