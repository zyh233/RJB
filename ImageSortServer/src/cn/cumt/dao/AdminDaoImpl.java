package cn.cumt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import cn.cumt.entity.Admin;
import cn.cumt.utils.JDBC;

public class AdminDaoImpl extends JDBC implements AdminDao{
	/**
	 * 用于管理员登录
	 */
	@Override
	public Admin login(Admin admin) {
		Admin admin2=null;
		String name = admin.getName();
		String password = admin.getPassword();
		String sql="SELECT * FROM `admin` WHERE name=? AND `password`=?";
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setString(1, name);
			pStatement.setString(2, password);
			ResultSet rs=pStatement.executeQuery();
			if(rs.next()){
				admin2 = new Admin();
				int aid=rs.getInt("aid");
				admin2.setAid(aid);
				admin2.setName(name);;
				admin2.setPassword(password);	
				boolean isroot=rs.getBoolean("isroot");
				admin2.setIsroot(isroot);
			}
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin2;
	}
	
	
	
	/**
	 * 用于按名字查找管理员
	 */
	@Override
	public boolean findByName(String name) {
		boolean flag = false;
		String sql="SELECT * FROM `admin` WHERE name=?";
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setString(1, name);
			ResultSet rs=pStatement.executeQuery();
			if (rs.next()) {
				flag = true;						
			}
			pStatement.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return flag;
	}

	
	/**
	 * 用于添加管理员
	 */
	@Override
	public boolean addAdmin(Admin admin) {
		boolean flag =false;
		String sql="INSERT INTO `admin` (`name`, `password`,`isroot`) VALUES (?, ?, ?)";
		String name = admin.getName();
		String password = admin.getPassword();
		boolean isroot = admin.isIsroot();
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setString(1, name);
			pStatement.setString(2, password);
			pStatement.setBoolean(3, isroot);
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
	 * 用于删除管理员
	 */
	@Override
	public boolean deleteAdmin(int aid) {
		String sql="DELETE FROM `admin` WHERE (`aid`=?)";
		boolean flag = false;
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setInt(1, aid);
			flag = pStatement.execute();
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}


	/**
	 * 用于更新管理员
	 */
	@Override
	public boolean updateAdmin(Admin admin) {
		boolean flag = false;
		int aid = admin.getAid();
		String name = admin.getName();
		String password = admin.getPassword();
		String sql="UPDATE `admin` SET `password`=?,`name`=? WHERE aid=?";
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setString(1, password);
			pStatement.setString(2, name);
			pStatement.setInt(3,aid );
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
	 * 查询全部的管理员
	 */
	@Override
	public List<Admin> findAllAdmins() {
		List<Admin> admins = new ArrayList<>();
		Admin admin = null;
		String sql = "SELECT * FROM `admin`";
		try {
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				admin = new Admin();
				admin.setAid(rs.getInt("aid"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("password"));
				admin.setIsroot(rs.getBoolean("isroot"));
				admins.add(admin);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admins;
	}



	@Override
	public int adminCount() {
		String sql = "SELECT COUNT(*) AS count FROM `admin`";
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

}
