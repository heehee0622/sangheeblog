package com.sanghee.test.sangheeblog.util.response;

public enum ResponseResult {
	OK(200, "요청 성공 했습니다."), FAIL(600, "요청 실패 했습니다."), BAD_REQUEST(601, "request validation failure.");

	private int code;
	private String msg;

	private ResponseResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public String getStrCode() {
		return Integer.toString(code);
	}
}