package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 404 NOT FOUND - [*]：数据不存在 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/10/25.
 */
public class UnfoundRecordEx extends AbstractEx {

	public UnfoundRecordEx(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

	public UnfoundRecordEx(String message, String... args) {
		super(HttpStatus.NOT_FOUND, message, args);
	}

	public UnfoundRecordEx(HttpStatus status, String message) {
		super(status, message);
	}

	public UnfoundRecordEx(HttpStatus status, String message, String... args) {
		super(status, message, args);
	}
}
