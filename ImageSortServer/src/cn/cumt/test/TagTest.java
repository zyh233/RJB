package cn.cumt.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import cn.cumt.entity.Tag;

public class TagTest {
	@Test
	public void testTag(){
		Tag[] tags =new Tag[4];
		List<Tag> list = new ArrayList<>();
		Tag tag1=new Tag("漂亮",3);
		Tag tag2=new Tag("美女",4);
		Tag tag3=new Tag("女神",2);
		Tag tag4=new Tag("female",5);
		tags[0]=tag1;
		tags[1]=tag2;
		tags[2]=tag3;
		tags[3]=tag4;
		list.add(tag4);
		list.add(tag3);
		list.add(tag2);
		list.add(tag1);
		Arrays.sort(tags);
		Collections.sort(list);
		for (int i = 0; i < tags.length; i++) {
			System.out.println(tags[i].toString());
		}
		System.out.println("============================");
		for (Tag tag : list) {
			System.out.println(tag.toString());
		}
		
	}

}
