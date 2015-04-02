package com.qinweiping.shopping.common;

import java.util.HashMap;

import org.json.JSONObject;

/**
 * a data class for 全局错误类型
 */
public class ErrorType {

	public static Integer NETWORK_ERROR = 1000;
	public static Integer NETWORK_REQUEST_ERROR = 1001;

	/** all error types */
	static HashMap<Integer, ErrorType> allErrorTypes = new HashMap<Integer, ErrorType>();
	static {
		allErrorTypes.put(-1, new ErrorType(-1, "网络错误，请稍后再试"));
		allErrorTypes.put(0,new ErrorType(0, "成功"));
		allErrorTypes.put(1,new ErrorType(1, "未知错误"));
		allErrorTypes.put(49, new ErrorType(49, "暂无数据"));
		allErrorTypes.put(100, new ErrorType(100, "未登录"));
		allErrorTypes.put(101, new ErrorType(101, "没有权限"));
		allErrorTypes.put(102, new ErrorType(102, "用户不存在"));
		allErrorTypes.put(1000, new ErrorType(1000, "网络异常，请重新设置网络"));
		allErrorTypes.put(1001, new ErrorType(1001, "网络异常，请稍后再试"));
	}
	

	private Integer errorCode;
	private String errorBody;
	private String ext;

	public ErrorType(int errorCode, String errorBody, String ext) {
		this.setErrorCode(errorCode);
		this.setErrorBody(errorBody);
		this.setExt(ext);
	}

	public ErrorType(int errorCode, String errorBody) {
		this.setErrorCode(errorCode);
		this.setErrorBody(errorBody);
	}

	public ErrorType(int errorCode) {
		this.setErrorCode(errorCode);
		this.setErrorBody(getErrorBody(errorCode));
	}

	/**
	 * 组装错误信息
	 */
	public static ErrorType constructErrorType(JSONObject json) {
		int errorCode = -1;
		return getErrorType(errorCode);
	}

	public static ErrorType constructErrorBody(JSONObject json) {
		if (json.has("message")) {
			int errorCode = AppUtil.getJsonIntegerValue(json, "error_code");
			String errorBody = AppUtil.getJsonStringValue(json, "message");
			return new ErrorType(errorCode, errorBody); // 自定义错误
			} else {
			return new ErrorType(-1);
		}
	}

	public static ErrorType getErrorType(int errorCode) {
		ErrorType error = allErrorTypes.get(errorCode);
		if (error == null) {
			error = allErrorTypes.get(-1);
		}
		return error;
	}

	public static String getErrorBody(int error) {
		ErrorType type = allErrorTypes.get(error);
		if (type != null) {
			return type.getErrorBody();
		}
		return "";
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorBody() {
		return errorBody;
	}

	public void setErrorBody(String errorBody) {
		this.errorBody = errorBody;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

}