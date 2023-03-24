package com.oracle.mvc03.dto;

public class Member {
	private String name;
	private String id;
	private String pw;
	private String email;
	
//	게터세터 맨 밑에 두어도 되고 여기에 둬도 잘 작동 되네
	@Override
	public String toString() {
		String returnStr = "[" + "이름 : " + this.name + " , " + this.id + "]";
		return returnStr;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
//	@Override
//	public String toString() {
//		String returnStr = "[" + "이름 : " + this.name + " , " + this.id + "]";
//		return returnStr;
//	}
	
	
	
}
