package com.emotiona.android.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

//把流转换成字符串
public class InputStreamToString {
	public static String isToString(InputStream is) throws IOException {
		byte[] bytes = new byte[1024];
		int len = 0;
		// 创建一个缓冲流
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = is.read(bytes)) != -1) {
			// 把字节读到缓冲流
			bos.write(bytes, 0, len);
		}
		// 转换成字节
		byte[] buffer = bos.toByteArray();
		bos.close();
		is.close();
		String str = new String(buffer, "utf-8");
		// 转换成字符串。并设置编码格式
		return str;
	}

	public static String MyIsToString(InputStream is) throws IOException {
		byte[] bytes = new byte[1024];
		int len = 0;
		// 创建一个缓冲流
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = is.read(bytes)) != -1) {
			// 把字节读到缓冲流
			bos.write(bytes, 0, len);
		}
		// 转换成字节
		byte[] buffer = bos.toByteArray();
		bos.close();
		is.close();
		String str = new String(buffer, "utf-8");
		// 转换成字符串。并设置编码格式
		return str;
	}
	/**
	 * 根据字符流转换为String
	 * @param is
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public static String getString(InputStream is, int length) throws Exception {

		byte[] buffer = new byte[length];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = is.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		byte[] byteArray = bos.toByteArray();
		bos.close();
		is.close();
		return new String(byteArray, "utf-8");
//		return new String(byteArray);
	}
}
