package cn.cumt.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.cumt.entity.Tag;

public class TagsMergeUtil {
	public static Set<Tag> stringMerge(String[] s1,List<Tag> list){
		Set<Tag> set = new HashSet<>();
		for(int i=0;i<s1.length;i++){
			for(int j=0;j<list.size();j++){
				double result = singleMerge(s1[i],list.get(j).getTag());
				if(result>0.4){
					if(s1[i].equals(list.get(j).getTag())){
						int weights = list.get(j).getWeights()+1;
						list.get(j).setWeights(weights);
						set.add(list.get(j));
					}
					else if(s1[i].length()<=list.get(j).getTag().length()){
						int weights = list.get(j).getWeights()+1;
						list.get(j).setWeights(weights);
						set.add(list.get(j));
					}else{
						Tag tag = new Tag();
						tag.setTag(s1[i]);
						tag.setWeights(1);
						set.add(tag);
					}
				}
			}
		}
		
		return set;
		
	}

	public static double singleMerge(String string, String string2) {
		if(string.equals(string2)){
			return 1;
		}
		char[] array1 = string.toCharArray();
		char[] array2 = string2.toCharArray();
		Set<Character> set1 =new HashSet<>();
		Set<Character> set2 =new HashSet<>();
		for (int i = 0; i < array1.length; i++) {
			set1.add(array1[i]);
		}
		for (int i = 0; i < array2.length; i++) {
			set2.add(array2[i]);
		}
		set1.retainAll(set2);
		int com=set1.size();
		double result =Math.sqrt(com)/(Math.sqrt(array1.length)*Math.sqrt(array2.length));
		return result;
		
		
	}
	
	public static List<Tag> stringMerge(String s,List<Tag> list){
		boolean flag = false;//是否合并
		for(int j=0;j<list.size();j++){
			String tag = list.get(j).getTag();
			double result = singleMerge(s,tag);
			if(result>0.4){
				if(s.equals(tag)){
					int weights = list.get(j).getWeights()+1;
					list.get(j).setWeights(weights);
				}
				else if(s.length()<=tag.length()){
					int weights = list.get(j).getWeights()+1;
					list.get(j).setWeights(weights);
				}else{
					Tag tag1 = new Tag();
					tag1.setTag(s);
					tag1.setWeights(1);
					list.add(tag1);
				}
				flag = true;
			}
		}
		//如果没有合并，则替换一个权值为1的标签
		//问题：如果没有权值为一的，则不会添加进去。
		if (!flag) {
			for(int j=0;j<list.size();j++){
				int w= list.get(j).getWeights();
				if(w==1){
					list.remove(j);
					Tag tag = new Tag();
					tag.setTag(s);
					tag.setWeights(1);
					list.add(tag);
					break;
				}
			}
		}
		return list;		
	}

}
