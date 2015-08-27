package com.emotiona.android.http;

import java.nio.charset.Charset;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
import android.annotation.SuppressLint;

/**
 * DESC加密工具类 SHA签名工具类
 * 
 * @author user yangguoqi
 * 
 */
public class PlatformCommonCrypt {
	// key的字母数量必修是8的倍数，并且大于24个字符
	private static final String DES_KEY = "^h$_0j9kwf$y7,@w%;o+f[]-";
	// 设置编码方式 
	private static final Charset ENCODE = Charset.forName("UTF-8");

	/**
	 * DES加密
	 * @param src 待加密字符串
	 * @return des使用秘钥key加密后的字符串
	 * @throws Exception
	 */
	@SuppressLint("NewApi")
	public static String encryptThreeDESECB(String src) {
		try {
			DESedeKeySpec dks = new DESedeKeySpec(DES_KEY.getBytes(ENCODE));
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("DESede");
			SecretKey securekey = keyFactory.generateSecret(dks);

			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, securekey);

			return new BASE64Encoder().encode(cipher.doFinal(src.getBytes()))
					.replaceAll("\r", "").replaceAll("\n", "");
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * DES解密
	 * @param src 待解密字符串
	 * @return 源字符串
	 * @throws Exception
	 */
	@SuppressLint("NewApi")
	public static String decryptThreeDESECB(String src) {
		try {
			// 通过base64,将字符串转成byte数组
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bytesrc = decoder.decodeBuffer(src);
			// 解密的key
			DESedeKeySpec dks = new DESedeKeySpec(DES_KEY.getBytes(ENCODE));
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("DESede");
			SecretKey securekey = keyFactory.generateSecret(dks);

			// Chipher对象解密
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, securekey);
			byte[] retByte = cipher.doFinal(bytesrc);

			return new String(retByte,ENCODE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * SHA1 摘要算法
	 * @param s 待摘要字符串
	 * @return 摘要后字符串
	 */
	@SuppressLint("NewApi")
	public final static String sha1(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes(ENCODE);
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("SHA-1");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();

			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}