package cn.cumt.service;

import java.util.List;

import cn.cumt.dao.UserDao;
import cn.cumt.dao.UserDaoImpl;
import cn.cumt.entity.User;
import cn.cumt.exception.UpdateFailException;
import cn.cumt.exception.UserRepeatException;

public class UserServiceImpl implements UserService{

	UserDao userDao=new UserDaoImpl();
	@Override
	public User Login(User user) {
		
		User user2=userDao.login(user);
		if(user2!=null)
			return user2;
		else
			return null;
	}

	@Override
	public boolean addUser(User user) throws UserRepeatException {
		if(userDao.findByName(user.getUsername())!=null){
			throw new UserRepeatException("用户名已存在!");
		}
		boolean flag=userDao.addUser(user);
		return flag;
	}

	@Override
	public boolean updateUser(User user) throws UpdateFailException {
		
		boolean updateUser = userDao.updateUser(user);
		return updateUser;
		
	}

	@Override
	public String getHobbies(int uid) {
		
		return userDao.getHobbies(uid);
	}

	@Override
	public void setHobbies(int uid, String hobbies) {
		userDao.setHobbies(uid, hobbies);
		
	}

	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}

	@Override
	public void deleteUser(int uid) {
		userDao.deleteUser(uid);
		
	}

	@Override
	public int userCount() {
		
		return userDao.userCount();
	}

	@Override
	public void updateScore(int uid, int score) {
		userDao.updateScore(uid, score);
		
	}

}
