package com.emotiona.android.base;

import java.util.Timer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.emotiona.android.MyAppaction;
import com.emotiona.android.R;
import com.emotiona.android.utils.ActivityManagerTool;
import com.emotiona.android.utils.MyPreferenceManager;
import com.emotiona.android.utils.MyTask;

/***
 * 
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @TODO 基类
 * @2015年7月14日
 *
 */

public abstract class BaseActivity extends FragmentActivity {
	// private MyAppaction app;
	/** 网络请求加载dialog */
	protected Dialog mLoadingDialog = null;
	/** activity 是否处于可见状态 */
	public boolean mIsActive = true;
	/** 系统提示框对象 */
	protected AlertDialog mSimpleAlertDialog = null;
	public MyPreferenceManager sharedpreferences;
	private Toast mToast;
	/** 是否为顶级视图 */
	public boolean mIsTop = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
		super.onCreate(savedInstanceState);
		setContentView(initPageLayoutID());// 设置
		Log.e("RUNNING-ACTIVITY", this.getClass().getName());//打印出每个activity的类名
		sharedpreferences = MyPreferenceManager.getInstance(this);
		initPageView();// 添加布局
		initPageViewListener();// 添加监听
		process(savedInstanceState);
		// 将每一个新开的acitivity放在activity管理集合中
		ActivityManagerTool.getActivityManager().add(this);
		if (mIsTop) {
			ActivityManagerTool.bottomActivities.add(this.getClass());
		}
	}
	protected void onStop() {
		super.onStop();
		mIsActive = false;
		// 关闭未关闭的loadingdialog 和 dialog
		dismissLoadingDialog();
		dismissSimpleAlertDialog();
	}

	protected void onDestroy() {
		super.onDestroy();
		// 将当前acitivity移除activity管理集合中
		ActivityManagerTool.getActivityManager().removeActivity(this);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	/**
	 * 退出程序操作
	 */
	public void exitApp() {
		ActivityManagerTool.getActivityManager().exit();
		android.os.Process.killProcess(android.os.Process.myPid());
		finish();
		System.exit(0);
	}
	/**
	 * 初始化布局
	 */
	protected abstract void initPageView();

	/**
	 * 初始化监听
	 */
	protected abstract void initPageViewListener();

	/**
	 * 设置布局
	 * 
	 * @return
	 */
	protected abstract int initPageLayoutID();

	/**
	 * 处理业务逻辑 数据加载
	 * 
	 * @param savedInstanceState
	 */
	protected abstract void process(Bundle savedInstanceState);

	/**
	 * 自定义 TOAST
	 * 
	 * @param msg
	 */
	public void showToast(String message) {
		if (null == mToast) {
			mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
			mToast.setGravity(Gravity.BOTTOM, 0, 0);
		} else {
			mToast.setText(message);
		}
		mToast.show();
	}


	/**
	 * 开启加载对话框,默认提示语为:努力加载中...
	 * 
	 */
	public void showLoadingDialog() {
		showLoadingDialog("正在加载数据中...");
	}

	/**
	 * 开启加载对话框
	 * 
	 * @param msg
	 *            需要显示的提示文本信息
	 */
	public void showLoadingDialog(String msg) {
		if (!mIsActive) {
			return;
		}
		if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
			return;
		}
		mLoadingDialog = customLoadingDialog("正在加载数据中...");
		mLoadingDialog.setCanceledOnTouchOutside(false);
		mLoadingDialog.show();
	}

	/**
	 * 取消加载对话框
	 */
	public void dismissLoadingDialog() {
		if (mLoadingDialog != null) {
			mLoadingDialog.dismiss();
		}
	}

	/**
	 * 自定义LoadingDialog,子类如果不想使用默认实现的Dialog可以重写此方法返回一个自定义的Dialog对象即可。
	 * 
	 * @param msg
	 *            loading提示语
	 * @return Dialog
	 */
	public Dialog customLoadingDialog(String msg) {
		ProgressDialog dialog = new ProgressDialog(this, R.style.LoadingDialog);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setMessage(msg);
		return dialog;
	}


	/**
	 * 开启SimpleAlertDialog
	 * 
	 * @param msg
	 *            需要显示的文本信息
	 * 
	 */
	public void showSimpleAlertDialog(String msg) {
		if (!mIsActive) {
			return;
		}
		mSimpleAlertDialog = new AlertDialog.Builder(this).setTitle("温馨提示").setMessage(msg).setPositiveButton("确定", null).create();
		mSimpleAlertDialog.setCanceledOnTouchOutside(false);
		mSimpleAlertDialog.show();
	}

	/**
	 * 开启SimpleAlertDialog
	 * 
	 * @param title
	 *            需要显示的文本标题
	 * @param msg
	 *            需要显示的文本信息
	 * 
	 */
	public void showSimpleAlertDialog(String msg, final Activity activity) {
		if (!mIsActive) {
			return;
		}
		mSimpleAlertDialog = new AlertDialog.Builder(this).setTitle("温馨提示").setMessage(msg).setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//启动Activity
			}
		}).create();
		mSimpleAlertDialog.setCanceledOnTouchOutside(false);
		mSimpleAlertDialog.show();
	}

	/**
	 * 开启SimpleAlertDialog
	 * 
	 * @param title
	 *            需要显示的文本标题
	 * @param msg
	 *            需要显示的文本信息
	 * @param btnName
	 *            确定按钮上的文本信息
	 * 
	 */
	public void showSimpleAlertDialog(String title, String msg, String btnName) {
		if (!mIsActive) {
			return;
		}
		mSimpleAlertDialog = new AlertDialog.Builder(this).setTitle(title).setMessage(msg).setPositiveButton(btnName, null).create();
		mSimpleAlertDialog.setCanceledOnTouchOutside(false);
		mSimpleAlertDialog.show();
	}

	/**
	 * 关闭mSimpleAlertDialog
	 */
	public void dismissSimpleAlertDialog() {
		if (mSimpleAlertDialog != null) {
			mSimpleAlertDialog.dismiss();
		}
	}

	/**
	 * 首页按返回键提示是否退出dialog
	 */
	public void showExitDialog() {
		new AlertDialog.Builder(BaseActivity.this).setTitle("温馨提示").setMessage("是否退出应用？").setPositiveButton("退出", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				//退出需要做的操作
				
				exitApp();
			}
		}).setNegativeButton("取消", null).show();
	}
	/**
	 * 底部导航栏点击返回跳到首页操作，如果是首页则执行退出操作
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 先判断是否是返回键
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
				dismissLoadingDialog();
			} else {
				// 判断是不是首页
				if (this.getClass() != ActivityManagerTool.indexActivity) {
					// 如果不是首页但是底部导航则执行跳转到首页操作
					if (ActivityManagerTool.getActivityManager().isBottomActivity(this)) {
						ActivityManagerTool.getActivityManager().backIndex(this);
					} else {
						return super.onKeyDown(keyCode, event);
					}
				} else {
					// 首页按返回键提示是否退出
					// showExitDialog();
					MyAppaction.BackKeyCount++;
					if (MyAppaction.BackKeyCount >= 2) {
						ActivityManagerTool.getActivityManager().exit();
						finish();
					} else {
						Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
						new Thread(new Runnable() {

							public void run() {
								// TODO Auto-generated method stub
								Timer timer = new Timer();
								timer.schedule(new MyTask(), 10000);
							}
						}).start();
					}
				}
			}
		}
		return false;
	}
}
