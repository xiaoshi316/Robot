package com.xiaoshi.chattingrobot.utils;

import java.util.TimerTask;

import com.xiaoshi.chattingrobot.MyAppaction;

public class MyTask extends TimerTask {
	/**
	 * 首页按返回键多少秒后，执行任务退出按键次数清零
	 * 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		MyAppaction.BackKeyCount = 0;
	}

}
