package com.xiaoshi.chattingrobot.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @TODO 打开或关闭软键盘
 * @2015年8月26日
 *
 */
public class KeyBoardUtils {
	/***
	 * 
	 * @param mEditText
	 * @param mContext
	 * @2015年8月26日下午5:12:31
	 * @TODO 打开软键盘
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public static void openKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * 
	 * @param mEditText
	 * @param mContext
	 * @2015年8月26日下午5:12:19
	 * @TODO 关闭软键盘
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public static void closeKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
	}
}
