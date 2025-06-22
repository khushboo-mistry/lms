package com.coachbar.lms.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Common standard response DTO", description = "Base response structure for all data")
public class Response<T> implements Serializable {

	@ApiModelProperty(value = "Response Object if successful case")
	private T data;

	@ApiModelProperty(value = "Defined status code", required = true)
	private Integer statusCode;

	@ApiModelProperty(value = "Defined status message", required = true)
	private String statusMessage;

	@ApiModelProperty(value = "Request status timestamp", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date timestamp;

	public Response() {
		super();
	}

	public Response(T data, Integer status, String message) {
		this(data, status, message, null);
	}

	public Response(Integer status, String message, List<ResponseError> errors) {
		this(null, status, message, errors);
	}

	public Response(T data, Integer statusCode, String statusMessage, List<ResponseError> errors) {
		super();
		this.data = data;
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.timestamp = new Date();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
