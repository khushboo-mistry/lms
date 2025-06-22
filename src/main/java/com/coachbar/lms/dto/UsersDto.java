package com.coachbar.lms.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Users", description = "User Information")
public class UsersDto {

	@ApiModelProperty(value = "Name", required = true, example = "Khushboo")
	private String name;

	@ApiModelProperty(value = "Surname", required = true, example = "Mistry")
	private String surname;

	@ApiModelProperty(value = "Address", required = true, example = "OP Road, Baroda, 390020")
	private String address;

	@ApiModelProperty(value = "Contact Number/Phone Number", required = true, example = "9876543210")
	private Date contactNumber;

	@ApiModelProperty(value = "Activation Status", required = true, example = "true")
	private boolean isActive;

	@ApiModelProperty(value = "User code", example = "KEHYBEHDTQ")
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
