package com.qinweiping.shopping.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

	public static String md5_encrypt(String str){
		try {		
			byte[] messageDigest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for (int i=0; i<messageDigest.length; i++){
				hexString.append(Integer.toHexString(0x0F & (messageDigest[i]>>4)));
				hexString.append(Integer.toHexString(0x0F & messageDigest[i]));	
			}
			return hexString.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
