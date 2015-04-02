package com.qinweiping.shopping.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

	/** BASE64 加密数据 */
	public static String encodeBASE64(String s) {
		if (s == null)
			return null;
		try {
			return new String(Base64.encodeBase64(s.getBytes()));
		} catch (Exception e) {
			return null;
		}
	}

	public static String encodeBASE64(byte[] s) {
		if (s == null)
			return null;
		try {
			return new String(Base64.encodeBase64(s));
		} catch (Exception e) {
			return null;
		}
	}

	/** BASE64 解密数据 */
	public static byte[] decodeBASE64(String s) {
		if (s == null)
			return null;
		try {
			return Base64.decodeBase64(s.getBytes());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将对象转换成byte数组，并将其进行base64编码
	 */
	public static String objectToString(Object obj) {
		String str = "";
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			str = Base64Util.encodeBASE64((baos.toByteArray()));
		} catch (IOException e) {
			e.printStackTrace();
			AppUtil.printLog("error", e.getMessage());
		}
		return str;
	}

	/**
	 * 对Base64格式的字符串进行解码
	 */
	public static Object stringToObject(String str) {
		Object obj = null;
		try {
			if (AppUtil.isNotEmpty(str)) {
				byte[] base64Bytes = Base64Util.decodeBASE64(str);
				ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				obj = ois.readObject();
			}
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
