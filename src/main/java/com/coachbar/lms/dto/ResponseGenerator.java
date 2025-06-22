package com.coachbar.lms.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseGenerator {

	private static final Logger LOG = LoggerFactory.getLogger(ResponseGenerator.class);

	static ObjectMapper jacksonMapper = new ObjectMapper();

	public static <T> Response<T> getResponse(T data, ResponseStatusCode responseStatusCode) {
		return new Response<>(data, responseStatusCode.getStatusCode(), responseStatusCode.getStatusMessage());
	}

	public static <T> Response<T> handleException(Exception ex) throws JsonProcessingException {
		String stacktrace = ExceptionUtils.getStackTrace(ex);
		LOG.error(stacktrace);
		List<ResponseError> listError = new ArrayList<>();
		listError.add(new ResponseError(null, stacktrace));
		return new Response<>(null, ResponseStatusCode.SE_3000.getStatusCode(),
				ResponseStatusCode.SE_3000.getStatusMessage(), listError);
	}

}
