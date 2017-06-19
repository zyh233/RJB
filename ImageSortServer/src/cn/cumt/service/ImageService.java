package cn.cumt.service;

import java.sql.SQLException;
import java.util.List;

import cn.cumt.entity.Image;
import cn.cumt.entity.Tag;

public interface ImageService {
	public List<Image> findAllImage();
	public List<Tag> findTagsById(int id);
	public boolean updateImageTags(String s,int id,String date);
	public int addImages(List<Image> images)throws SQLException;
	
	public List<Image> exportImages();
	public List<Image> findByIndex(String index);
	
	public int imageCount();
	
	public void deleteImage(int id);
	
	public String findName(int id);

}
