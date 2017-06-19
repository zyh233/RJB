package cn.cumt.test;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.cumt.dao.ImageDao;
import cn.cumt.dao.ImageDaoImpl;
import cn.cumt.dao.UserDao;
import cn.cumt.dao.UserDaoImpl;
import cn.cumt.entity.Image;
import cn.cumt.entity.Tag;
import cn.cumt.entity.User;
import cn.cumt.exception.UserRepeatException;
import cn.cumt.service.UserService;
import cn.cumt.service.UserServiceImpl;

public class CurdTest {
	@Test
	public void testAdduser(){
		UserService userService=new UserServiceImpl();
		User user=new User();
		user.setUsername("lisi");
		user.setPassword("352");
		user.setPhone("1234587");
		user.setEmail("zs@cumt.edu.cn");
		try {
			userService.addUser(user);
		} catch (UserRepeatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testFindByName(){
		UserDao userDao=new UserDaoImpl();
		String name="lily";
		User user = userDao.findByName(name);
		System.out.println(user);
	}
	@Test
	public void testLogin(){
		UserDao userDao=new UserDaoImpl();
		User user=new User();
		user.setUsername("zhangsan");
		user.setPassword("12355");
		System.out.println(userDao.login(user));
	}
	@Test
	public void testDelete(){
		UserDao userDao=new UserDaoImpl();
		userDao.deleteUser(1);
	}
	@Test
	public void testFindAll(){
		UserDao userDao=new UserDaoImpl();
		List<User> list = userDao.findAll();
		for (User user : list) {
			System.out.println(user);
		}
	}
	@Test
	public void testUpdate(){
		User user=new User("d","123456","987654","zs@163.com","√¿≈Æ");
		UserDao userDao=new UserDaoImpl();
		
		System.out.println(userDao.updateUser(user));
	}
	@Test
	public void testFindAllImage(){
		ImageDao imageDao = new ImageDaoImpl();
		List<Image> list = imageDao.findAllImage();
		for (Image image : list) {
			System.out.println(image);
		}
	}
	@Test
	public void testFindTagsById(){
		ImageDao imageDao = new ImageDaoImpl();
		List<Tag> s = imageDao.findTagsById(2);
		if (s==null) {
			System.out.println("null");
		}else
		{
			for (Tag string : s) {
				System.out.println(string.toString());
			}
		}
	}
	@Test
	public void testUpdateTags(){
		ImageDao imageDao = new ImageDaoImpl();
		SimpleDateFormat format=new SimpleDateFormat("yyyy.MM.dd'T'HH:mm:ss'Z'");
		String date = format.format(new Date());
		boolean flag = imageDao.updateImageTags("∆Ø¡¡,female", 1,date);
		System.out.println(flag);
	}
	@Test
	public void testAddImage(){
		ImageDao imageDao = new ImageDaoImpl();
		List<Image> list = new ArrayList<>();
		Image image;
		for(int i=1;i<=20;i++)
		{
			 image = new Image("≤ÕÃ¸"+i+".jpg",null,null);
			 list.add(image);
		}
		try {
			int images = imageDao.addImages(list);
			System.out.println(images);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testHobbies(){
		UserDao dao = new UserDaoImpl();
		String hobbies = dao.getHobbies(2);
		System.out.println(hobbies);
	}
	
	@Test
	public void testInsertHobbies(){
		UserDao dao = new UserDaoImpl();
		int uid = 3;
		String hobbies = "∑Áæ∞";
		dao.setHobbies(uid, hobbies);
		
	}
	
	@Test
	public void testCount(){
		UserDao dao = new UserDaoImpl();
		int userCount = dao.userCount();
		System.out.println(userCount);
		
	}
}
