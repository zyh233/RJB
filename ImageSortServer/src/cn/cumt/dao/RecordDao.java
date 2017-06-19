package cn.cumt.dao;

import java.util.List;
import java.util.Map;

import cn.cumt.entity.Record;

public interface RecordDao {
	public List<Record> queryRecordById(int uid);
	public int addRecords(List<Record> list);
	public boolean addRecord(Record record);
	public boolean findRecord(int uid,int id);
	
	public boolean updateRecord(Record record);
	
	public int queryUserCount();
	
	public int queryImageCount();
	
	public List<Integer> user2ImageNum();
	
	
	public List<Integer> queryUids();
	
	
	public List<Integer> image2UserNum();
	
	public List<Integer> queryIds();
	
	public void deleteRecord(int uid,int id);
}
