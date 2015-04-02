package com.qinweiping.shopping.common;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptTool {
	/**
	 * 加密内容
	 * 
	 * @param content
	 * @param key
	 * @return
	 */
	public static String encryptAES(String content, String key) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			byte[] hexStr = cipher.doFinal(content.getBytes("utf-8"));
			return Base64Util.encodeBASE64(hexStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密内容
	 * 
	 * @param key
	 * @param encryptedContent
	 * @return
	 */
	public static String getDecryptedContent(byte[] keyBytes, String encryptedContent) {
		if(AppUtil.isEmpty(encryptedContent))
			return encryptedContent;
		byte contentBytes[] = Base64Util.decodeBASE64(encryptedContent);
		try {
			SecretKeySpec spec = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, spec);
			cipher.getBlockSize();
			String res = new String(cipher.doFinal(contentBytes));
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedContent;
	}
}
