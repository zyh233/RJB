package cn.cumt.entity;

import java.io.Serializable;

public class Image implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String tags;
	private String url;
	private String date;
	
	public Image() {
		super();
	}
	public Image(String name, String tags, String date) {
		this.name = name;
		this.tags = tags;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", tags="
				+ tags + ", url=" + url + "]";
	}
	
}
