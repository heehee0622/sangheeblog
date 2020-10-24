package com.sanghee.test.sangheeblog.util.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sanghee.test.sangheeblog.model.BasicVo;
import com.sanghee.test.sangheeblog.model.DataInterface;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData {
	private String message = ResponseResult.OK.getMsg();
	private String code = ResponseResult.OK.getStrCode();
	private DataInterface singleData = null;
	private List< ? extends DataInterface> data = null;
	
	public ResponseData(String message, String code) {
		this.message = message;
		this.code = code;
	}

	public ResponseData() {
	}

	public ResponseData(String message, String code, List< ? extends DataInterface> data) {
		this.message = message;
		this.code = code;
		this.data = data;
	}
	
	public static ResponseData SUCCESS(List<? extends DataInterface> data){
		ResponseData jsonData = new ResponseData(ResponseResult.OK.getMsg(), ResponseResult.OK.getStrCode());
		jsonData.setData(data);
		return jsonData;
	}
	
	public static ResponseData SUCCESS(){
		ResponseData jsonData = new ResponseData(ResponseResult.OK.getMsg(), ResponseResult.OK.getStrCode());
		jsonData.setSingleData(new BasicVo());
		return jsonData;
	}
	
	public static ResponseData SUCCESS(DataInterface data){
		ResponseData jsonData = new ResponseData(ResponseResult.OK.getMsg(), ResponseResult.OK.getStrCode());
		jsonData.setSingleData(data);
		return jsonData;
	}

	public static ResponseData SUCCESS(DataInterface data, ResponseResult  result, String message){
		ResponseData jsonData = new ResponseData(result.getMsg(), result.getStrCode());
		jsonData.setSingleData(data);
		return jsonData;
	}
	
	public static ResponseData FAIL(String msg){
		return new ResponseData(msg, ResponseResult.FAIL.getStrCode());
	}
	
	public static ResponseData FAIL(int code, String msg){
		return new ResponseData(msg, String.valueOf(code));
	}
	
	public static ResponseData FAIL(ResponseResult Error){
		return new ResponseData(Error.getMsg(), Error.getStrCode());
	}
	
	public static ResponseData FAIL(String code,String message ,List<? extends DataInterface> data){
		ResponseData jsonData = new ResponseData(message, code);
		jsonData.setData(data);
		return jsonData;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public List<? extends DataInterface> getData() {
		return data;
	}
	
	public void setData(List<? extends DataInterface> data) {
		this.data = data;
	}

	public DataInterface getSingleData() {
		return singleData;
	}

	public void setSingleData(DataInterface singleData) {
		this.singleData = singleData;
	}

}
