package com.coachbar.lms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Lendings", description = "Lendings Information/Book Issue Records")
public class LendingsDto {

	@ApiModelProperty(value = "Book Name", required = true, example = "Head First Java")
	private String bookName;

	@ApiModelProperty(value = "Book Code", required = true, example = "KEHYBEHDTQ")
	private String bookCode;

	@ApiModelProperty(value = "User Name", required = true, example = "Khushboo Mistry")
	private String userName;

	@ApiModelProperty(value = "User Code", required = true, example = "KEHYBEHDTQ")
	private String userCode;

	@ApiModelProperty(value = "Issued Date", required = true, example = "2024-01-01")
	private String issuedDate;

	@ApiModelProperty(value = "Due Date", required = true, example = "2024-02-01")
	private String dueDate;

	@ApiModelProperty(value = "Return Date", required = true, example = "2024-01-15")
	private String returnDate;

	@ApiModelProperty(value = "Name", required = true, example = "INR: 5/day")
	private String fineChargerPerDay;

	@ApiModelProperty(value = "Name", required = true, example = "0")
	private String fineAmount;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getFineChargerPerDay() {
		return fineChargerPerDay;
	}

	public void setFineChargerPerDay(String fineChargerPerDay) {
		this.fineChargerPerDay = fineChargerPerDay;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(String fineAmount) {
		this.fineAmount = fineAmount;
	}

}
