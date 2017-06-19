package cn.cumt.utils;

public class TagsToArray {
	public static String[] strToArray(String tags){
		if(tags!=null){
			String[] split = tags.split(",");
			String[] result =new String[split.length];
			for (int i=0;i<split.length;i++) {
				String[] split2 = split[i].split(":");
				result[i]=split2[0];
			}
			return result;
		}
		return null;
		
		
	}
}
