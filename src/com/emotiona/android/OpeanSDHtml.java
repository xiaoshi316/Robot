package com.emotiona.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import com.emotiona.android.R;
import com.emotiona.android.zipfile.ZipCopyUtil;

public class OpeanSDHtml extends Activity implements OnClickListener {
	private Button btn_zip, btn_show;
	private WebView wv_test;
	// 设置解压目的路径
	public static String OUTPUT_DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AndroidSdk";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opensdhtml);
		btn_zip = (Button) this.findViewById(R.id.btn_zip);
		btn_show=(Button) findViewById(R.id.btn_show);
		wv_test = (WebView) findViewById(R.id.wv_test);
		btn_zip.setOnClickListener(this);
		btn_show.setOnClickListener(this);
		wv_test.setVisibility(View.VISIBLE);
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_zip:
			final ProgressDialog dialog = new ProgressDialog(OpeanSDHtml.this);
			dialog.setTitle("提示");
			dialog.setMessage("正在解压文件，请稍后！");
			dialog.show();// 显示对话框
			new Thread() {
				public void run() {
					// 在新线程中以同名覆盖方式解压
					ZipCopyUtil.copyAndUnzip(OpeanSDHtml.this, "android_bundle.zip", OUTPUT_DIRECTORY);
					dialog.cancel();// 解压完成后关闭对话框
				}
			}.start();
			break;
		case R.id.btn_show:
//			wv_test.getSettings().setAllowFileAccess(true);
			wv_test.getSettings().setJavaScriptEnabled(true);
			wv_test.loadUrl("file:///"+OUTPUT_DIRECTORY+"/html/addBankcards.html");
			break;
		default:
			break;
		}
	}
}
