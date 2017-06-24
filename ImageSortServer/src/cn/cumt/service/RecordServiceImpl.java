package cn.cumt.service;

import java.util.List;
import cn.cumt.dao.RecordDao;
import cn.cumt.dao.RecordDaoImpl;
import cn.cumt.entity.Record;
import cn.cumt.exception.InsertRecordException;

public class RecordServiceImpl implements RecordService{
	RecordDao recordDao = new RecordDaoImpl();
	@Override
	public List<Record> queryRecordById(int uid) {
		
		return recordDao.queryRecordById(uid);
	}
	@Override
	public void addRecords(List<Record> list) throws InsertRecordException {
		int num = recordDao.addRecords(list);
		if(num!=list.size()){
			throw new InsertRecordException((list.size()-num)+"记录插入失败");
		}
		
	}
	/**
	 * 添加一条记录
	 * @param record 要添加的记录
	 * @return 返回添加成功与否，true为成功，false为失败
	 */
	@Override
	public boolean addRecord(Record record) {
		int uid = record.getUid();
		int id = record.getId();
		if(recordDao.findRecord(uid, id)){
			return recordDao.updateRecord(record);
		}		
		return recordDao.addRecord(record);
	}
	
	/**
	 * 打标签的用户数量
	 */
	@Override
	public int queryUserCount() {
		
		return recordDao.queryUserCount();
	}
	
	/**
	 * 打过标签的图片数量
	 */
	@Override
	public int queryImageCount() {
		
		return recordDao.queryImageCount();
	}
	@Override
	public List<Integer> user2ImageNum() {
		
		return recordDao.user2ImageNum();
	}
	@Override
	public List<Integer> queryUids() {
		
		return recordDao.queryUids();
	}
	@Override
	public List<Integer> image2UserNum() {		
		return recordDao.image2UserNum();
	}
	@Override
	public List<Integer> queryIds() {
		return recordDao.queryIds();
	}
	@Override
	public void deleteRecord(int uid, int id) {
		recordDao.deleteRecord(uid, id);
		
	}
	
	@Override
	public List<Record> findRecordsByUid(int uid) {
		
		return recordDao.findRecordsByUid(uid);
	}

}
