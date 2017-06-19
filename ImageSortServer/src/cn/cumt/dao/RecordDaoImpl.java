package cn.cumt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;

import cn.cumt.entity.Record;
import cn.cumt.utils.JDBC;

public class RecordDaoImpl extends JDBC implements RecordDao{
	
	
	/**
	 * @param uid 用户id
	 * @return 返回用户标记过的图片和标签
	 */
	@Override
	public List<Record> queryRecordById(int uid) {
		List<Record> list=new ArrayList<>();
		Record record = null;
		String sql="SELECT * FROM `record` WHERE uid='"+uid+"'";
		try {
			ResultSet rs=statement.executeQuery(sql);
			while (rs.next()) {
				record = new Record();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String tags = rs.getString("tags");
				record.setUid(uid);
				record.setId(id);
				record.setName(name);
				record.setTags(tags);
				list.add(record);				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * @param list 要插入的记录集合
	 */
	@Override
	public int addRecords(List<Record> list) {
		int count = 0;
		try 
		{
			String sql = "INSERT INTO `record` VALUES (?, ?, ?, ?)";
			PreparedStatement pStatement =null;
			for (Record record : list) {
				
					int uid = record.getUid();
					int id = record.getId();
					String name = record.getName();
					String tags = record.getTags();
					pStatement = (PreparedStatement) connection.prepareStatement(sql);
					pStatement.setInt(1, uid);
					pStatement.setInt(2, id);
					pStatement.setString(3, name);
					pStatement.setString(4, tags);
					int i= pStatement.executeUpdate();
					if(i!=0) count++;
			}
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
		
		
	}
	/**
	 * 添加一条记录
	 * @param record 要添加的记录
	 * @return 返回添加成功与否，true为成功，false为失败
	 */
	@Override
	public boolean addRecord(Record record) {
		try{
			String sql = "INSERT INTO `record` VALUES (?, ?, ?, ?)";
			PreparedStatement pStatement =null;				
			int uid = record.getUid();
			int id = record.getId();
			String name = record.getName();
			String tags = record.getTags();
			pStatement = (PreparedStatement) connection.prepareStatement(sql);
			pStatement.setInt(1, uid);
			pStatement.setInt(2, id);
			pStatement.setString(3, name);
			pStatement.setString(4, tags);
			int i = pStatement.executeUpdate();
			pStatement.close();
			if(i!=0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 用于查找是否该有记录
	 */
	@Override
	public boolean findRecord(int uid, int id) {
		String sql="SELECT * FROM `record` WHERE uid=? AND id=?";
		try {
			PreparedStatement pStatement = (PreparedStatement) connection.prepareStatement(sql);
			pStatement.setInt(1, uid);
			pStatement.setInt(2, id);
			ResultSet rs=pStatement.executeQuery();
			if(rs.next()){
				return true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 用于更新Record
	 */
	@Override
	public boolean updateRecord(Record record) {
		String sql="UPDATE `record` SET `tags`=? WHERE `uid`=? AND `id`=?";
		int uid = record.getUid();
		int id = record.getId();
		String tags = record.getTags();
		try {
			PreparedStatement pStatement = (PreparedStatement) connection.prepareStatement(sql);
			pStatement.setString(1, tags);
			pStatement.setInt(2, uid);
			pStatement.setInt(3, id);
			int i=pStatement.executeUpdate();
			if(i!=0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public int queryUserCount() {
		String sql = "SELECT COUNT(distinct uid) AS count FROM `record`";
		
		int count = 0;
		try {
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()){
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	@Override
	public int queryImageCount() {
		String sql = "SELECT COUNT(distinct id) AS count FROM `record`";		
		int count = 0;
		try {
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()){
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
		
	}
	
	/**
	 * 查找用户id-图片数量对
	 */
	@Override
	public List<Integer> user2ImageNum() {	
		List<Integer> ids = new ArrayList<>();
		try {									
			List<Integer> uids = queryUids();
			ResultSet rs = null;
			for (Integer uid : uids) {

				String sql = "SELECT COUNT(id) AS count FROM `record` WHERE uid ='"+ uid + "'";
				rs = statement.executeQuery(sql);
				int count = 0;
				if (rs.next()) {
					count = rs.getInt("count");
				}
				ids.add(count);
			}
			if(rs!=null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}
	
	@Override
	public List<Integer> queryUids() {
		List<Integer> uids = new ArrayList<>();
		ResultSet rs = null;
		try {
			String sql = "SELECT distinct uid  FROM `record`";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				int uid = rs.getInt("uid");
				uids.add(uid);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uids;
	}
	/**
	 * 
	 * @return 返回
	 */
	@Override
	public List<Integer> image2UserNum() {	
		List<Integer> uids = new ArrayList<>();
		try {									
			List<Integer> ids = queryIds();
			ResultSet rs = null;
			for (Integer id : ids) {

				String sql = "SELECT COUNT(uid) AS count FROM `record` WHERE id ='"+ id + "'";
				rs = statement.executeQuery(sql);
				int count = 0;
				if (rs.next()) {
					count = rs.getInt("count");
				}
				uids.add(count);
			}
			if (rs!=null) {
				rs.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uids;
	}
	@Override
	public List<Integer> queryIds(){
		List<Integer> ids = new ArrayList<>();
		ResultSet rs = null;
		try {
			String sql = "SELECT distinct id  FROM `record`";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				int uid = rs.getInt("id");
				ids.add(uid);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;		
	}
	
	/**
	 * 删除记录
	 */
	@Override
	public void deleteRecord(int uid, int id) {
		String sql = "DELETE FROM `record` WHERE uid=? AND id=?";
		try {
			PreparedStatement pStatement = (PreparedStatement) connection.prepareStatement(sql);
			pStatement.setInt(1, uid);
			pStatement.setInt(2, id);
			pStatement.executeUpdate();
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	

}
