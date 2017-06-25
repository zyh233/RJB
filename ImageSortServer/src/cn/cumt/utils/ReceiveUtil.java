package cn.cumt.utils;

import java.util.ArrayList;
import java.util.List;

import cn.cumt.entity.Record;
import cn.cumt.entity.Tag;
import cn.cumt.service.ImageService;
import cn.cumt.service.ImageServiceImpl;

public class ReceiveUtil {
	/**
	 * 
	 * @return 返回已接受的图片集
	 */
	public static List<Integer> QueryReceive(List<Record> records){
		List<Integer> list = new ArrayList<>();
		ImageService service = new ImageServiceImpl();
		for (Record record : records) {
			int id = record.getId();
			String tag = record.getTags();			
			List<Tag> tags = service.findTagsById(id);
			if(tags!=null){
				for (Tag tag2 : tags) 
				{
					double d = TagsMergeUtil.singleMerge(tag, tag2.getTag());
					if (d>0.4) {
						list.add(id);
						break;
					}
				}
			}
			
		}
		return list;
		
	}

}
