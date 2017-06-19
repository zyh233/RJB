package cn.cumt.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.cumt.dao.RecordDao;
import cn.cumt.dao.RecordDaoImpl;
import cn.cumt.entity.Record;
import cn.cumt.exception.InsertRecordException;
import cn.cumt.service.RecordService;
import cn.cumt.service.RecordServiceImpl;

public class RecordTest {
	
	RecordService rs = new RecordServiceImpl();
	@Test
	public void testQueryRecord(){
		
		int uid = 1;
		List<Record> records = rs.queryRecordById(uid);
		for (Record record : records) {
			System.out.println(record.toString());
		}
		
	}
	@Test
	public void testAddRecords(){
		
		Record record = new Record(1,2,"atm1.jpg","atm机");
		Record record2 = new Record(1,3,"atm2.jpg","atm机,银行");
		Record record3 = new Record(2,3,"atm2.jpg","atm机,银行");
		List<Record> list = new ArrayList<>();
		list.add(record);
		list.add(record2);
		list.add(record3);
		try {
			rs.addRecords(list);
		} catch (InsertRecordException e) {
			System.out.println(e.toString());
		}
		
	}
	
	
	@Test
	public void testUserCount(){
		RecordDao dao = new RecordDaoImpl();
		int userCount = dao.queryUserCount();
		System.out.println(userCount);
	}
	
	@Test
	public void testImageCount(){
		RecordDao dao = new RecordDaoImpl();
		int imageCount = dao.queryImageCount();
		System.out.println(imageCount);
	}
	
	@Test
	public void testDelete(){
		RecordDao dao = new RecordDaoImpl();
		dao.deleteRecord(5, 5);;
	}
	
	

}
