package cn.cumt.entity;

public class User2Image {
	private int uid;
	private int id;
	private String username;
	private String name;
	private String tag;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "User2Image [uid=" + uid + ", id=" + id + ", username="
				+ username + ", name=" + name + ", tag=" + tag + "]";
	}
	
	

}
