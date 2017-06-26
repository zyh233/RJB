package cn.cumt.service;

import java.util.List;

import cn.cumt.entity.Record;
import cn.cumt.exception.InsertRecordException;

public interface RecordService {
	public List<Record> queryRecordById(int uid);
	public void addRecords(List<Record> list)throws InsertRecordException;
	public boolean addRecord(Record record);
	
	public int queryUserCount();
	
	public int queryImageCount();
	public List<Integer> queryUids();
	public List<Integer> user2ImageNum();
	
	public List<Integer> image2UserNum();
	
	public List<Integer> queryIds();
	
	public void deleteRecord(int uid,int id);
	
	public List<Record> findRecordsByUid(int uid);
	
	public String queryTag(int uid,int id);
	
	public boolean updateRecord(Record record);
	
}
