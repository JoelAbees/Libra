package com.lms.model;

public class User {
	private int userID;
	private String name;
	private String userName;
	private String password;
	private String email;
	private String phoneNumber;
	private String type;
	
	/*
	 * public void setUserID(int userID) {this.userID = userID;} public void
	 * setName(String name) {this.name = name;} public void setUserName(String
	 * userName) {this.userName = userName;} public void setPassword(String
	 * password) {this.password = password;} public void setEmail(String email)
	 * {this.email = email;} public void setPhoneNumber(String phoneNumber)
	 * {this.phoneNumber = phoneNumber;} public void setType(String type) {this.type
	 * = type;}
	 */
	public void setDetails(String name,String userName, String password , String email , String phoneNumber, String type ) {
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.type = type;
	}
	
	public int getUserID() {return userID;}
	public String getName() {return name;}
	public String getUserName() {return userName;}
	public String getPassword() {return password;}
	public String getEmail() {return email;}
	public String getPhoneNumber() {return phoneNumber;}
	public String getType() {return type;}
	
	public String toString() {
		return "User [userID=" + userID + ", name=" + name + ", userName=" + userName + ", password=" + password + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", type=" + type + "]";
	}
}
