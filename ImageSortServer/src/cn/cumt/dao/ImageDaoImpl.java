package cn.cumt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import cn.cumt.entity.Image;
import cn.cumt.entity.Tag;
import cn.cumt.utils.JDBC;

public class ImageDaoImpl extends JDBC implements ImageDao{
	/**
	 * 找到所有的图片
	 */
	@Override
	public List<Image> findAllImage() {
		List<Image> list=new ArrayList<>();
		Image image=null;
		
		String sql="SELECT * FROM `image`";
		try {
			ResultSet resultSet=statement.executeQuery(sql);
			while (resultSet.next()) {
				
				image=new Image();
				image.setId(resultSet.getInt("id"));
				String name=resultSet.getString("name");
				image.setName(name);
				String s=resultSet.getString("tags");
				image.setTags(s);
				String url="http://114.115.142.214:8080/ImageSortServer/images/"+name;
				image.setUrl(url);
				String date = resultSet.getString("finish_time");
				image.setDate(date);
				list.add(image);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 通过图片的id找到图片的标签
	 */
	@Override
	public List<Tag> findTagsById(int id) {
		List<Tag> tags=new ArrayList<>();
		Tag tag=null;
		String sql="SELECT tags FROM `image` WHERE id='"+id+"'";
		try {
			ResultSet resultSet=statement.executeQuery(sql);
			if(resultSet.next()){
				String s=resultSet.getString(1);
				if(s==null||s.equals("")){
					return null;
				}
				String[] split = s.split(",");
				for (String string : split) {
					String[] split2 = string.split(":");
					tag = new Tag();
					tag.setTag(split2[0]);
					Integer weight = Integer.valueOf(split2[1]);
					tag.setWeights(weight);
					tags.add(tag);
				}
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tags;
	}
	/**
	 * 更新图片信息
	 */
	@Override
	public boolean updateImageTags(String s, int id,String date) {
		String sql="UPDATE `image` SET `tags`='"+s+"',`finish_time`='"+date+"' WHERE id='"+id+"'";
		try {
			int flag = statement.executeUpdate(sql);
			if(flag!=0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 添加图片
	 */
	@Override
	public int addImages(List<Image> images) throws SQLException {
		int count = 0;
		PreparedStatement pStatement =null;
		for (Image image : images) {
			String sql = "INSERT INTO `image` (`name`, `tags`, `finish_time`) VALUES (?, NULL, NULL)";
			pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setString(1, image.getName());
			int i=pStatement.executeUpdate();
			if(i!=0){
				count++;
			}
		}
		pStatement.close();
		return count;
	}
	/**
	 * 导出图片，找到有标签的图片
	 */
	@Override
	public List<Image> exportImages() {
		String sql = "SELECT * FROM `image` WHERE tags IS NOT NULL";
		List<Image> exports = new ArrayList<>();
		Image image = null;
		try {
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				image = new Image();
				image.setId(rs.getInt("id"));
				image.setName(rs.getString("name"));
				image.setTags(rs.getString("tags"));
				image.setDate(rs.getString("finish_time"));
				exports.add(image);						
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exports;
	}
	/**
	 * 图片数量
	 */
	@Override
	public int imageCount() {
		String sql = "SELECT COUNT(*) AS count FROM `image`";		
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
	 * 
	 * 删除图片
	 */
	@Override
	public void deleteImage(int id) {
		String sql="DELETE FROM `image` WHERE (`id`=?)";
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setInt(1, id);
			pStatement.execute();
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 查找图片名称
	 */
	@Override
	public String findName(int id) {
		String sql="SELECT `name` FROM `image` WHERE `id`=?";
		String name = null;
		try {
			PreparedStatement pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setInt(1, id);
			ResultSet rs = pStatement.executeQuery();
			if(rs.next()){
				name = rs.getString("name");
			}
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	
	

}
