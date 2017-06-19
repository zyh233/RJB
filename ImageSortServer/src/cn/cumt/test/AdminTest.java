package cn.cumt.test;

import java.util.List;

import org.junit.Test;

import cn.cumt.dao.AdminDao;
import cn.cumt.dao.AdminDaoImpl;
import cn.cumt.entity.Admin;
import cn.cumt.service.AdminService;
import cn.cumt.service.AdminServiceImpl;

public class AdminTest {
	AdminService service = new AdminServiceImpl();
	@Test
	public void testLogin(){
		Admin admin = new Admin();
		admin.setName("root");
		admin.setPassword("root");
		Admin admin2 = service.login(admin);
		System.out.println(admin2.toString());
		
	}
	
	
	@Test
	public void testAdd(){
		Admin admin = new Admin();
		admin.setName("root");
		admin.setPassword("root");
		admin.setIsroot(false);
		boolean flag = service.addAdmin(admin);
		System.out.println(flag);
	}
	
	
	@Test
	public void testFindAll(){
		List<Admin> admins = service.findAllAdmins();
		System.out.println(admins.toString());
	}
	
	@Test
	public void testCount(){
		AdminDao dao = new AdminDaoImpl();
		int adminCount = dao.adminCount();
		System.out.println(adminCount);
	}

}
