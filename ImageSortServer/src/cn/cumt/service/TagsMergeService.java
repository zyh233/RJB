package cn.cumt.service;

import java.util.List;
import java.util.Set;

import cn.cumt.entity.Tag;

public interface TagsMergeService {
	public Set<Tag> mergeTags(String[] s1,List<Tag> list);

}
