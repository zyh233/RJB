package cn.cumt.service;

import java.util.List;

import cn.cumt.dao.CascadeDao;
import cn.cumt.entity.User2Image;

public class CascadeService {
	
	CascadeDao dao = new CascadeDao();
	public List<User2Image> user2Image(){
		return dao.user2Image();
		
	}
	
	public List<User2Image> image2User(){
		
		return dao.image2User();
	}

}
