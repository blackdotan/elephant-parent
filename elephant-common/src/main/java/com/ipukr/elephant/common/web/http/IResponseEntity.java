package com.ipukr.elephant.common.web.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2020/2/25.
 */
public class IResponseEntity<T> extends ResponseEntity<T> {

	public IResponseEntity(HttpStatus statusCode) {
		super(statusCode);
	}

	public IResponseEntity(T body, HttpStatus statusCode) {
		super(body, statusCode);
	}

	public IResponseEntity(MultiValueMap<String, String> headers, HttpStatus statusCode) {
		super(headers, statusCode);
	}

	public IResponseEntity(T body, MultiValueMap<String, String> headers, HttpStatus statusCode) {
		super(body, headers, statusCode);
	}




}
