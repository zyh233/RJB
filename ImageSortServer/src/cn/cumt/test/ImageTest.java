package cn.cumt.test;

import java.util.List;

import org.junit.Test;

import cn.cumt.dao.ImageDao;
import cn.cumt.dao.ImageDaoImpl;
import cn.cumt.entity.Tag;

public class ImageTest {
	ImageDao dao = new ImageDaoImpl();
	@Test
	public void testFindById(){
		
		List<Tag> tags = dao.findTagsById(7);
		if(tags!=null)
		 System.out.println(tags.toString());
		else
			System.err.println("null");
		
	}
	
	@Test
	public void testCount(){
		int imageCount = dao.imageCount();
		System.out.println(imageCount);
	}
	
	@Test
	public void testName(){
		String name = dao.findName(5);
		System.out.println(name);
	}

}
