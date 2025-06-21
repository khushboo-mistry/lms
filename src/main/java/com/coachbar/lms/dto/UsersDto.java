package com.coachbar.lms.dto;

import java.util.Date;

public class UsersDto {

	private String name;

	private String surname;

	private String address;

	private Date contactNumber;

	private boolean isActive;

	private String userCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Date contactNumber) {
		this.contactNumber = contactNumber;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
