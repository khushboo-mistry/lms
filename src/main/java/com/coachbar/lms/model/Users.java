package com.coachbar.lms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "USERS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Users extends BaseDomainObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "NAME", length = 500, nullable = false)
	private String name;

	@Column(name = "SURNAME", nullable = false)
	private String surname;

	@Column(name = "ADDRESS", nullable = false)
	private String address;

	@Column(name = "CONTACT_NUMBER", nullable = false)
	private Date contactNumber;

	@Column(name = "IS_ACTIVE", nullable = false)
	private boolean isActive;

	@Column(name = "USER_CODE", nullable = false)
	private String userCode;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Lendings> lendings;
	
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