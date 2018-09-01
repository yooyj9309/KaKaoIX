package com.example.demo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.demo.exception.ServerErrorException;


public class SecurityUtil {
	/**
	 * 비밀 번호를 암호화 하기 위한 함수
	 * 
	 * @param str
	 * @return 암호화된 string (hash)
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encryptSHA256(String str) {
		String encryptString = "";

		MessageDigest digestMessage=null;
		try {
			digestMessage = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			throw new ServerErrorException("비밀 번호 암호화중 실패");
		}
		digestMessage.update(str.getBytes());
		byte byteData[] = digestMessage.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		encryptString = sb.toString();

		return encryptString;
	}

}
