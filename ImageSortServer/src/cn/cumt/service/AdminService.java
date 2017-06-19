package cn.cumt.service;

import java.util.List;

import cn.cumt.entity.Admin;

public interface AdminService {
	public Admin login(Admin admin);
	public boolean findByName(String name);
	
	public boolean addAdmin(Admin admin);
	
	public boolean deleteAdmin(int aid);
	
	
	public boolean updateAdmin(Admin admin);
	
	public List<Admin> findAllAdmins();
	
	public int adminCount();

}
