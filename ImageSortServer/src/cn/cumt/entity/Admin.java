package cn.cumt.entity;

public class Admin {
	private int aid;
	private String name;
	private String password;
	private boolean isroot;
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isIsroot() {
		return isroot;
	}
	public void setIsroot(boolean isroot) {
		this.isroot = isroot;
	}
	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", name=" + name + ", password="
				+ password + ", isroot=" + isroot + "]";
	}
	
	

}
