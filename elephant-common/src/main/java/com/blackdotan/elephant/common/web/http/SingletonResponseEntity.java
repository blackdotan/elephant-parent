package com.blackdotan.elephant.common.web.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2020/2/25.
 */
public class SingletonResponseEntity<T> extends HttpEntity {

	private HttpStatus statusCode;

	private SingletonResponseEntityBody body;

	/**
	 * @param data
	 * @param statusCode
	 */
	public SingletonResponseEntity(T data, HttpStatus statusCode) {
		this(data, true, "请求成功", null, new HttpHeaders(), statusCode);
	}

	/**
	 * @param data
	 * @param success
	 * @param msg
	 * @param statusCode
	 */
	public SingletonResponseEntity(T data,
	                                Boolean success,
	                                String msg,
	                                HttpStatus statusCode) {
		this(data, success, msg, null, new HttpHeaders(), statusCode);
	}

	/**
	 * @param headers
	 * @param statusCode
	 */
	public SingletonResponseEntity(MultiValueMap<String, String> headers,
	                                HttpStatus statusCode) {
		super(headers);
		this.statusCode = statusCode;
	}

	/**
	 * @param data
	 * @param headers
	 * @param statusCode
	 */
	public SingletonResponseEntity(T data,
	                                MultiValueMap<String, String> headers,
	                                HttpStatus statusCode) {
		this(data, true, "请求成功", null,headers, statusCode);
	}

	/**
	 * @param data
	 * @param success
	 * @param msg
	 * @param headers
	 * @param statusCode
	 */
	public SingletonResponseEntity(T data,
	                                Boolean success,
	                                String msg,
	                                Integer code,
	                                MultiValueMap<String, String> headers,
	                                HttpStatus statusCode) {
		body = new SingletonResponseEntityBody();
		body.setData(data);
		body.setSuccess(success);
		body.setMsg(msg);
		body.setCode(code == null ? statusCode.value() : code);
		body.setData(data);
		this.statusCode = statusCode;
	}


	@Override
	public Object getBody() {
		return body;
	}


	/**
	 * Create a builder with the given status.
	 * @param status the response status
	 * @return the created builder
	 * @since 4.1
	 */
	public static SingletonResponseEntity.BodyBuilder status(HttpStatus status) {
		return new SingletonResponseEntity.BodyBuilder(status);
	}

	/**
	 * Create a builder with the status set to {@linkplain HttpStatus#OK OK}.
	 * @return the created builder
	 * @since 4.1
	 */
	public static SingletonResponseEntity.BodyBuilder ok() {
		return new SingletonResponseEntity.BodyBuilder(HttpStatus.OK);
	}

	/**
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> SingletonResponseEntity ok(T data){
		return new SingletonResponseEntity(data, HttpStatus.OK);
	}


	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class BodyBuilder {

		private HttpStatus status;

		private HttpHeaders headers = new HttpHeaders();

		/**
		 * 提示信息
		 */
		private String msg = "请求成功";
		/**
		 * 是否成功
		 */
		private Boolean success = true;
		/**
		 * 提示信息
		 */
		private Integer code;
		/**
		 * 数据
		 * */
		private Object data;


		public BodyBuilder(HttpStatus status) {
			this.status = status;
		}

		public SingletonResponseEntity.BodyBuilder header(String headerName, String... headerValues) {
			for (String headerValue : headerValues) {
				this.headers.add(headerName, headerValue);
			}
			return this;
		}


		public SingletonResponseEntity.BodyBuilder msg(String msg) {
			this.msg = msg;
			return this;
		}

		public SingletonResponseEntity.BodyBuilder success(Boolean success) {
			this.success = success;
			return this;
		}

		public SingletonResponseEntity.BodyBuilder code(Integer code) {
			this.code = code;
			return this;
		}

		public SingletonResponseEntity.BodyBuilder data(Object data) {
			this.data = data;
			return this;
		}

		public SingletonResponseEntity build() {
			return new SingletonResponseEntity(data, success, msg, code, this.headers, this.status);
		}

		public SingletonResponseEntity body(Object data) {
			this.data = data;
			return new SingletonResponseEntity(data, success, msg, code, this.headers, this.status);
		}
	}


	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class SingletonResponseEntityBody {
		/**
		 * 提示信息
		 */
		private String msg;
		/**
		 * 是否成功
		 */
		private Boolean success;
		/**
		 * 提示信息
		 */
		private Integer code;
		/**
		 * 数据
		 * */
		private Object data;


	}


}
