package com.coachbar.lms.dto;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Common error DTO", description = "Base error structure for error & details")
public class ResponseError {

	@ApiModelProperty(value = "Error code")
    private String code;

	@ApiModelProperty(value = "Error message")
    private String message;

	@ApiModelProperty(value = "Error details")
    private List<Map<String, Object>> detail;

    public ResponseError(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ResponseError(String code, String message, List<Map<String, Object>> detail) {
        super();
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<Map<String, Object>> getDetail() {
        return detail;
    }

}
