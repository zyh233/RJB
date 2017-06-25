package cn.cumt.entity;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private int uid;
	private String username;
	private String password;
	private String phone;
	private String email;
	private String hobbies;
	private int score;
	public User() {
		super();
	}
	
	public User(String username, String password, String phone,
			String email,String hobbies) {
		super();
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.hobbies = hobbies;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password="
				+ password + ", phone=" + phone + ", email=" + email
				+ ", hobbies=" + hobbies + ", score=" + score + "]";
	}

	
	
	
}
