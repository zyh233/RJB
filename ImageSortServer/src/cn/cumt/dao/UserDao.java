package cn.cumt.dao;
import java.util.List;

import cn.cumt.entity.User;

public interface UserDao {
	public User login(User user);
	public boolean addUser(User user);
	public User findByName(String name);
	public void deleteUser(int uid);
	public List<User> findAll();
	public boolean updateUser(User user);
	
	public String getHobbies(int uid);
	
	public void setHobbies(int uid,String hobbies);
	
	public int userCount();
}
