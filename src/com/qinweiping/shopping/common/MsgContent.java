package com.qinweiping.shopping.common;

import java.util.Arrays;

public class MsgContent {
	public byte msg[] = new byte[1024];
	public int len;

	public MsgContent() {
		Arrays.fill(msg,(byte) 0);
	}
	
	public String readString(){
		if(len > 0){
			byte recvArr[] = new byte[len];
			for (int i=0; i<len; i++) {
				recvArr[i] = msg[i];
			}
			return new String(recvArr);
		}
		return null;
	}
	
	public byte[] read(){
		if(len > 0){
			byte recvArr[] = new byte[len];
			for (int i=0; i<len; i++) {
				recvArr[i] = msg[i];
			}
			return recvArr;
		}
		return null;
	}
}
