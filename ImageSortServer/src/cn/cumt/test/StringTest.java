package cn.cumt.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import cn.cumt.dao.ImageDao;
import cn.cumt.dao.ImageDaoImpl;
import cn.cumt.entity.Tag;
import cn.cumt.utils.TagsToList;
import cn.cumt.utils.TagsMergeUtil;
import cn.cumt.utils.TagsToString;

public class StringTest {

	public static void main(String[] args) {
		String[] s1={"¹·","»ÆÃ«¹·","ÀÇ","¹þÊ¿Ææ"};
		List<Tag> list = new ArrayList<>();
		list.add(new Tag("½ðÃ«¹·", 1));
		list.add(new Tag("¶þ¹þ", 1));
		Set<Tag> set = TagsMergeUtil.stringMerge(s1, list);
		String tags = TagsToString.tagsToString(set);
		System.out.println(tags);
		Iterator<Tag> it = set.iterator();
		while (it.hasNext()) {
			Tag str = it.next();
			System.out.println(str.toString());			
		}

	}
	@Test
	public void testSingleMerge(){
		String s1="½ðÃ«¹·";
		String s2="¹·";
		double singleMerge = TagsMergeUtil.singleMerge(s1, s1);
		double singleMerge1 = TagsMergeUtil.singleMerge(s1, s2);
		double singleMerge2 = TagsMergeUtil.singleMerge(s2, s2);
		System.out.println(singleMerge);
		System.out.println(singleMerge1);
		System.out.println(singleMerge2);
	}
	@Test
	public void testStringMerge(){
	
	}
	@Test
	public void testStringToTags(){
		String string = "½ðÃ«¹·:3,¹þÊ¿Ææ:1";
		List<Tag> tags = TagsToList.stringToTags(string);
		for (Tag tag : tags) {
			System.out.println(tag.toString());
		}
	}
	
	
	@Test
	public void testDate(){
		SimpleDateFormat format=new SimpleDateFormat("yyyy.MM.dd'T'HH:mm:ss'Z'");
		String date = format.format(new Date());	
		System.out.println(date);
	}
	
	@Test
	public void testSplit(){
		String string = "hsdk,hdjfk";
		String[] split = string.split(",");
		for (String string2 : split) {
			
			System.out.println(string2);
		}
		
	}
	
	@Test
	public void testSQL(){
		ImageDao dao = new ImageDaoImpl();
		dao.findTagsById('3');
		
	}

}
