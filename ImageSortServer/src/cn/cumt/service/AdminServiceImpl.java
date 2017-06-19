package cn.cumt.service;

import java.util.List;

import cn.cumt.dao.AdminDao;
import cn.cumt.dao.AdminDaoImpl;
import cn.cumt.entity.Admin;

public class AdminServiceImpl implements AdminService{
	AdminDao adminDao = new AdminDaoImpl();
	@Override
	public Admin login(Admin admin) {
		return adminDao.login(admin);
	}

	@Override
	public boolean findByName(String name) {
		
		return adminDao.findByName(name);
	}

	@Override
	public boolean addAdmin(Admin admin) {
		boolean flag = adminDao.findByName(admin.getName());
		if(flag){
			return false;
		}
		return adminDao.addAdmin(admin);
		
	}

	@Override
	public boolean deleteAdmin(int aid) {
		
		return adminDao.deleteAdmin(aid);
	}

	@Override
	public boolean updateAdmin(Admin admin) {			
		return adminDao.updateAdmin(admin);
	}

	@Override
	public List<Admin> findAllAdmins() {
		
		return adminDao.findAllAdmins();
	}

	@Override
	public int adminCount() {
		
		return adminDao.adminCount();
	}

}
