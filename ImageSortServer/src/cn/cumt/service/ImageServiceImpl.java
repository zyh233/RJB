package cn.cumt.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.cumt.dao.ImageDao;
import cn.cumt.dao.ImageDaoImpl;
import cn.cumt.entity.Image;
import cn.cumt.entity.Tag;
import cn.cumt.utils.TagsMergeUtil;
import cn.cumt.utils.TagsToArray;

public class ImageServiceImpl implements ImageService{
	ImageDao imageDao = new ImageDaoImpl();

	@Override
	public List<Image> findAllImage() {
		
		return imageDao.findAllImage();
	}

	@Override
	public List<Tag> findTagsById(int id) {
		
		return imageDao.findTagsById(id);
	}

	@Override
	public boolean updateImageTags(String s, int id,String date) {
		
		return imageDao.updateImageTags(s, id,date);
	}

	@Override
	public int addImages(List<Image> images) throws SQLException {
		
		return imageDao.addImages(images);
	}

	@Override
	public List<Image> exportImages() {
		
		return imageDao.exportImages();
	}
	/**
	 * 按照索引查找图片
	 * @param index 索引
	 */
	@Override
	public List<Image> findByIndex(String index) {
		List<Image> list = new ArrayList<>();
		List<Image> images = imageDao.exportImages();
		for (Image image : images) {			
			String tags = image.getTags();
			String[] arrays = TagsToArray.strToArray(tags);
			for (String string : arrays) {
				double merge = TagsMergeUtil.singleMerge(string, index);
				if(merge>0.4){
					list.add(image);
					break;
				}
			}
		}
		return list;		
	}

	@Override
	public int imageCount() {
		
		return imageDao.imageCount();
	}

	@Override
	public void deleteImage(int id) {
		imageDao.deleteImage(id);
		
	}

	@Override
	public String findName(int id) {
		
		return imageDao.findName(id);
	}

}
