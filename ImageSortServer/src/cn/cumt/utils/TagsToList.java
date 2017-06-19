package cn.cumt.utils;

import java.util.ArrayList;
import java.util.List;

import cn.cumt.entity.Tag;

public class TagsToList {
	/**
	 * �Ѵ����ݿ���������ַ�����ǩת����List���ϱ�ǩ
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
