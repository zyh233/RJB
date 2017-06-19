package cn.cumt.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.cumt.entity.Tag;
import cn.cumt.utils.TagsMergeUtil;

public class TagsMergeServiceImpl implements TagsMergeService{

	@Override
	public Set<Tag> mergeTags(String[] s1,List<Tag> list) {
		Set<Tag> set = null;
		if(list==null){
			set = new HashSet<>();
			for (int i = 0; i < s1.length; i++) {
				Tag tag = new Tag(s1[i],1);
				set.add(tag);
			}
		}else if(list.size()<6){
			set = new HashSet<>(list);
			for (int i = 0; i < s1.length; i++) {
				Tag tag = new Tag(s1[i],1);
				set.add(tag);
			}
		}else {
			set=TagsMergeUtil.stringMerge(s1, list);
		}
		return set;
	}

}
