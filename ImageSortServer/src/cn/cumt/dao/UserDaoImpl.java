package cn.cumt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import cn.cumt.entity.User;
import cn.cumt.utils.JDBC;

public class UserDaoImpl extends JDBC implements UserDao{
	/**
	 * 用户登录
	 */
	@Override
	public User login(User user) {
		User user2=null;
		String username = user.getUsername();
		String password = user.getPassword();
		String sql="SELECT * FROM `user` WHERE username=? AND `password`=?";
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			ResultSet rs=pStatement.executeQuery();
			if(rs.next()){
				user2 = new User();
				int uid=rs.getInt("uid");
				String phone=rs.getString("phone");
				String email=rs.getString("email");
				String hobbies = rs.getString("hobbies");
				int score = rs.getInt("score");
				user2.setUid(uid);
				user2.setUsername(username);
				user2.setPassword(password);
				user2.setPhone(phone);
				user2.setEmail(email);
				user2.setHobbies(hobbies);
				user2.setScore(score);
			}
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user2;
	}
	/**
	 * 添加用户
	 */
	public boolean addUser(User user) {
		String username=user.getUsername();
		String password=user.getPassword();
		String phone=user.getPhone();
		String email=user.getEmail();
		boolean flag = false;
		String sql="INSERT INTO `user` (`username`, `password`, `phone`, `email`) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			pStatement.setString(3, phone);
			pStatement.setString(4, email);
			int i=pStatement.executeUpdate();
			if(i!=0){
				flag = true;
			}
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 按用户名查找用户
	 */
	@Override
	public User findByName(String name) {
		User user=null;
		String sql="SELECT * FROM `user` WHERE username=?";
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setString(1, name);
			ResultSet rs=pStatement.executeQuery();
			while (rs.next()) {
				user = new User();
				int uid=rs.getInt("uid");
				String username=rs.getString("username");
				String password=rs.getString("password");
				String phone=rs.getString("phone");
				String email=rs.getString("email");
				String hobbies = rs.getString("hobbies");
				user.setUid(uid);
				user.setUsername(username);
				user.setPassword(password);
				user.setPhone(phone);
				user.setEmail(email);
				user.setHobbies(hobbies);								
			}
			pStatement.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return user;
	}
	/**
	 * 删除用户
	 */
	@Override
	public void deleteUser(int uid) {
		String sql="DELETE FROM `user` WHERE (`uid`=?)";
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setInt(1, uid);
			boolean execute = pStatement.execute();
			System.out.println(execute);
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查找所有用户
	 */
	@Override
	public List<User> findAll() {
		List<User> list=new ArrayList<>();
		String sql="SELECT * FROM `user`";
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs=pStatement.executeQuery();
			while (rs.next()) {
				User user=new User();
				int uid=rs.getInt("uid");
				String username=rs.getString("username");
				String password=rs.getString("password");
				String phone=rs.getString("phone");
				String email=rs.getString("email");
				String hobbies = rs.getString("hobbies");
				user.setUid(uid);
				user.setUsername(username);
				user.setPassword(password);
				user.setPhone(phone);
				user.setEmail(email);
				user.setHobbies(hobbies);
				list.add(user);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * 修改用户信息
	 */
	@Override
	public boolean updateUser(User user) {
		String sql="UPDATE `user` SET `password`=?,`phone`=?,`email`=?,`hobbies`=?,`username`=? WHERE `uid`=?";
		boolean flag = false;
		String username=user.getUsername();
		String password=user.getPassword();
		String phone=user.getPhone();
		String email=user.getEmail();
		String hobbies = user.getHobbies();
		int uid =user.getUid();
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setString(1, password);
			pStatement.setString(2, phone);
			pStatement.setString(3, email);
			pStatement.setString(4, hobbies);
			pStatement.setString(5, username);
			pStatement.setInt(6, uid);
			int i=pStatement.executeUpdate();
			if(i!=0){
				flag = true;
			}
			pStatement.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 获取用户爱好
	 */

	@Override
	public String getHobbies(int uid) {
		String sql="SELECT hobbies FROM `user` WHERE uid=?";
		String hobbies = null;
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setInt(1, uid);
			ResultSet rs=pStatement.executeQuery();
			if(rs.next()){
				hobbies = rs.getString(1);
			}
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hobbies;
	}
	/**
	 * 更新用户喜好
	 */
	@Override
	public void setHobbies(int uid, String hobbies) {
		String sql="UPDATE `user` SET `hobbies`=? WHERE `uid`=?";
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setString(1, hobbies);
			pStatement.setInt(2, uid);
			pStatement.executeUpdate();
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 用户数量
	 */

	@Override
	public int userCount() {
		String sql = "SELECT COUNT(*) AS count FROM `user`";
		int count = 0;
		try {
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()){
				count = rs.getInt("count");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 更新用户积分
	 */
	@Override
	public void updateScore(int uid, int score) {
		String sql="UPDATE `user` SET `score`=? WHERE `uid`=?";		
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setInt(1, score);
			pStatement.setInt(2, uid);
			pStatement.executeUpdate();
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
}
