package cn.cumt.entity;

public class Tag implements Comparable<Tag>{

	private String tag;
	private int weights;
	
	public Tag() {
		
	}

	public Tag(String tag, int weights) {
		super();
		this.tag = tag;
		this.weights = weights;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public int getWeights() {
		return weights;
	}


	public void setWeights(int weights) {
		this.weights = weights;
	}


	@Override
	public int compareTo(Tag o) {
		// TODO Auto-generated method stub
		if(this.weights==o.weights){
			return 0;
		}else if(this.weights>o.weights){
			return 1;
		}else {
			return -1;
		}
		
	}


	@Override
	public String toString() {
		return tag + ":" + weights;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Tag)){
			return false;
		}
		Tag t = (Tag) obj;
		if(this.tag==t.tag)
			return true;
		else {
			return false;
		}
	}

}
