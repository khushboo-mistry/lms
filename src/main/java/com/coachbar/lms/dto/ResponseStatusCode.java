package com.coachbar.lms.dto;

public enum ResponseStatusCode {
	
	//S--> SUCCESS code series 1xxx
	S_1000(1000,"Successful."),
	S_1001(1001,"Book Added Successfully."),
	S_1002(1002,"Book Updated Successfully."),
	S_1003(1003,"Book Deleted Successfully."),
	S_1004(1004,"Book Issued Successfully."),

	//CE--> CLIENT ERROR code series 2xxx
	CE_2000(2000,"Unsuccessful."),
	CE_2001(2000,"Book Not Found."),

	//SE--> SERVER ERROR code series 3xxx
	SE_3000(3000,"Error In Processing Request"),
	
	SE_4000(4000,"Unauthorized Access");
	private Integer statusCode;
	
	private String statusMessage;
	
	ResponseStatusCode(int statusCode, String statusMessage) {
		this.statusCode= statusCode;
		this.statusMessage = statusMessage;
	}
	
	public Integer getStatusCode() {
		return this.statusCode;
	}
	
	public String getStatusMessage() {
		return this.statusMessage;
	}

}
