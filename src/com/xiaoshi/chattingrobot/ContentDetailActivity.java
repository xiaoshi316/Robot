package com.xiaoshi.chattingrobot;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.xiaoshi.chattingrobot.base.BaseActivity;

/***
 * 
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @TODO
 * @2015年8月5日
 *
 */
public class ContentDetailActivity extends BaseActivity {
	private WebView wv_content;

	@Override
	protected void initPageView() {
		// TODO Auto-generated method stub
		wv_content=(WebView) super.findViewById(R.id.wv_content);
	}

	@Override
	protected void initPageViewListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int initPageLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_content_detail;
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void process(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Intent intent=getIntent();
		String url=intent.getStringExtra("url");
		wv_content.getSettings().setJavaScriptEnabled(true);
		wv_content.loadUrl(url);
	}
}
