package cn.cumt.service;

import java.util.List;

import cn.cumt.entity.User;
import cn.cumt.exception.UpdateFailException;
import cn.cumt.exception.UserRepeatException;

public interface UserService {
	public User Login(User user);
	public boolean addUser(User user)throws UserRepeatException;
	public boolean updateUser(User user)throws UpdateFailException;
	
	public String getHobbies(int uid);
	
	public void setHobbies(int uid,String hobbies);
	
	public List<User> findAll();
	
	public void deleteUser(int uid);
	
	public int userCount();
		
}
