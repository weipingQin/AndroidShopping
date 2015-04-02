package com.qinweiping.shopping.common;

import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.common.http.HttpHelper;

public class BaseApi {

	private String TAG = "BaseApi";
	protected Context mContext;
	protected HttpHelper httpHelper;

	public BaseApi(Context context) {
		this.mContext = context;
		this.httpHelper = new HttpHelper(context);
	}

	public String getResponseByGet(String httpUrl) {
		String response = httpHelper.mGet(httpUrl);
		if (AppUtil.isNotEmpty(response)) {
			return response;
		}
		return "";
	}
	
	public JSONObject getJsonResponseByGet(String httpUrl) {
		try {
			String response = httpHelper.mGet(httpUrl);
			if (AppUtil.isNotEmpty(response)) {
				return new JSONObject(response);
			}
		} catch (JSONException e) {
			AppUtil.printLog(TAG, e.getMessage());
		}
		return new JSONObject();
	}

	public JSONObject getJsonResponseByPost(String httpUrl, List<NameValuePair> params) {
		try {
			String response = httpHelper.mPost(httpUrl, params);
			if (AppUtil.isNotEmpty(response)) {
				return new JSONObject(response);
			}
		} catch (JSONException e) {
			AppUtil.printLog(TAG, e.getMessage());
		}
		return new JSONObject();
	}
	
	public JSONObject getJsonResponseByPut(String httpUrl,List<NameValuePair> params){
		try {
			String response = httpHelper.mPut(httpUrl, params);
			if (AppUtil.isNotEmpty(response)) {
				return new JSONObject(response);
			}
		} catch (JSONException e) {
			AppUtil.printLog(TAG, e.getMessage());
		}
		return new JSONObject();
	}
	
	public JSONObject getJsonResponseByDelete(String httpUrl){
		try {
			String response = httpHelper.mDelete(httpUrl);
			if (AppUtil.isNotEmpty(response)) {
				return new JSONObject(response);
			}
		} catch (JSONException e) {
			AppUtil.printLog(TAG, e.getMessage());
		}
		return new JSONObject();
	}
	
	public String getResponseByPost(String httpUrl,List<NameValuePair> params) {
		return httpHelper.mPost(httpUrl, params);
	}
	
	public String getResponseByPut(String httpUrl,List<NameValuePair> params){
		return httpHelper.mPut(httpUrl, params);
	}
	
	public String getResponseByDelete(String httpUrl){
		return httpHelper.mDelete(httpUrl);
	}
	
	/**
	 * 获得 var name={}; 格式的内�?	 */
	public JSONObject getJsonResponseByGet(String httpUrl, String varName) {
		String response = httpHelper.mGet(httpUrl);
		try {
			varName = varName + "=";
			if (response.indexOf(varName) != -1) {
				int beginIndex = response.indexOf(varName) + varName.length();
				int endIndex = response.lastIndexOf("};") + 1;
				response = response.substring(beginIndex, endIndex);
			}
			return new JSONObject(response);
		} catch (JSONException e) {
			AppUtil.printLog(TAG, response);
			AppUtil.printLog(TAG, e.getMessage());
		}
		return new JSONObject();
	}

}