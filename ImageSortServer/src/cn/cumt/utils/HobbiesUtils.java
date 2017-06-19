package cn.cumt.utils;

public class HobbiesUtils {
	
	public static String addHobbies(String s,String hobbies){
		String[] hobby = hobbies.split(",");
		if(hobby.length<4)
		{
			for (int i = 0; i < hobby.length; i++) {
				if(hobby[i].equals(s)){
					String result = adjustSqe(hobby, i);
					return result;
				}
				if(TagsMergeUtil.singleMerge(s, hobby[i])>0.4){
					String result = adjustSqe(hobby, i);
					return result;
				}
			}
			
			return hobbies+","+s;
		}else 
		{			
			for (int i = 0; i < hobby.length; i++) {
				if(hobby[i].equals(s)){
					String result = adjustSqe(hobby, i);
					return result;
				}
				if(TagsMergeUtil.singleMerge(s, hobby[i])>0.4){
					String result = adjustSqe(hobby, i);
					return result;
				}
			}
			//≤ªœ‡µ»
			StringBuffer sb = new StringBuffer();
			for(int i = 1; i< hobby.length; i++){
				sb.append(hobby[i]+",");
			}
			sb.append(s);
			return sb.toString();
		}		
		
	}
	public static String adjustSqe(String[] hobby,int i){
		String temp = hobby[i];
		for(int j = i;j<hobby.length-1;j++){
			hobby[j]=hobby[j+1];
		}
		hobby[hobby.length-1] = temp;
		StringBuffer sb = new StringBuffer();
		for(int j = 1; j < hobby.length; j++){
			if(j==hobby.length-1)
				sb.append(hobby[j]);
			else
				sb.append(hobby[i]+",");
		}
		
		return sb.toString();		
	}

}
