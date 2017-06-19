package cn.cumt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.cumt.entity.User2Image;
import cn.cumt.utils.JDBC;

public class CascadeDao extends JDBC{
	public List<User2Image> user2Image() {
		List<User2Image> user2Images = new ArrayList<>();
		User2Image user2Image = null;
		String sql = "select `user`.`uid` AS `uid`,`user`.`username` AS `username`,`record`.`id` AS `id`,"
				+ "`record`.`name` AS `name`,`record`.`tags` AS `tags` from (`record` join `user`) "
				+ "where (`user`.`uid` = `record`.`uid`) order by `record`.`uid`";
		try {
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				user2Image = new User2Image();
				user2Image.setUid(rs.getInt("uid"));
				user2Image.setId(rs.getInt("id"));
				user2Image.setUsername(rs.getString("username"));
				user2Image.setName(rs.getString("name"));
				user2Image.setTag(rs.getString("tags"));
				user2Images.add(user2Image);				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user2Images;
	}
	
	
	public List<User2Image> image2User() {
		List<User2Image> image2Users = new ArrayList<>();
		User2Image user2Image = null;
		String sql = "select `record`.`id` AS `id`,`record`.`name` AS `name`,"
				+ "`user`.`uid` AS `uid`,`user`.`username` AS `username`,`record`.`tags` AS `tags` from (`record` join `user`) "
				+ "where (`user`.`uid` = `record`.`uid`) order by `record`.`id`";
		try {
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				user2Image = new User2Image();
				user2Image.setUid(rs.getInt("uid"));
				user2Image.setId(rs.getInt("id"));
				user2Image.setUsername(rs.getString("username"));
				user2Image.setName(rs.getString("name"));
				user2Image.setTag(rs.getString("tags"));
				image2Users.add(user2Image);				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return image2Users;
	}
}
