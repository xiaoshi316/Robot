package com.emotiona.android.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

public class Utils {

	/**
	 * px像素，转换成dp
	 * 
	 * @param context
	 * @param Pixels
	 * @return
	 */
	public static int pixelsToDip(Context context, int Pixels) {
		int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, Pixels, context.getResources().getDisplayMetrics());
		return dip;
	}

	/**
	 * 
	 * 
	 * @param str
	 *            需要判空的字符串
	 * @return boolean 是否为空字符串
	 */
	/***
	 * 
	 * isNull:(字符串判空,true 为不是空字符串，false 为空字符串)<br/>
	 * TODO 字符串判空,true 为不是空字符串，false 为空字符串
	 * @author emotiona<br/>
	 * @Email emtiona_xiaoshi@126.com 	
	 * @param str
	 * @return
	 * @since JDK 1.7
	 */
	public static boolean isNull(String str) {
		if (str != null && !"".equals(str.trim()) && !"".equals(str) && !"null".equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 将时间戳 格式化 成时间<br>
	 * 
	 * 返回格式：yyyy-MM-dd
	 * */
	public static String formatTime(String time, String partten) {
		long timestamp = Long.parseLong(time);
		SimpleDateFormat sdf = new SimpleDateFormat(partten);
		String date = sdf.format(new Date(timestamp * 1000));
		return date;
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static String getSystemTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static String getSystemTimetwo() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * 获取系统当前时间
	 *
	 * @return
	 */
	public static String getSystemTimeThree() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * 将double类型数据转换为百分比格式，并保留小数点前IntegerDigits位和小数点后FractionDigits位
	 * 
	 * @param d
	 * @param IntegerDigits
	 * @param FractionDigits
	 * @return
	 */
	public static String getPercentFormat(double d, int FractionDigits) {
		NumberFormat nf = java.text.NumberFormat.getPercentInstance();
		nf.setMaximumIntegerDigits(5);// 小数点前保留几位
		nf.setMinimumFractionDigits(FractionDigits);// 小数点后保留几位
		String str = "";
		if (d >= 0)
			str = "+" + nf.format(d);
		else
			str = nf.format(d);
		return str;
	}

	/***
	 * 获取百分号
	 * 
	 * @param d
	 * @param FractionDigits
	 * @return
	 */
	public static String getPercentFormatTwo(double d, int FractionDigits) {
		NumberFormat nf = java.text.NumberFormat.getPercentInstance();
		nf.setMaximumIntegerDigits(5);// 小数点前保留几位
		nf.setMinimumFractionDigits(FractionDigits);// 小数点后保留几位
		String str = "";
		str = nf.format(d);
		return str;
	}

	/***
	 * 点击某一控件隐藏软件盘的方法
	 * 
	 * @param activity
	 * @param view
	 */
	public static void hideSoftKeyboard(Activity activity, View view) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	/**
	 * 将流转成字节数组
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static byte[] stream2Bytes(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = is.read(buffer, 0, 1024)) != -1) {
			baos.write(buffer, 0, length);
		}
		baos.flush();
		return baos.toByteArray();
	}

	/**
	 * 根据URL得到图片
	 */
	public static Bitmap getBitmapFromUrl(String urlStr) throws Exception {
		URL url = new URL(urlStr);
		URLConnection connection = url.openConnection();
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		Bitmap bitmap = BitmapFactory.decodeStream(bis);
		bis.close();
		inputStream.close();
		return bitmap;
	}

	/**
	 * 保存文件到指定路径，后缀名不修改
	 */
	public static void saveImageToSD(Bitmap bitmap, String path, String fileName) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File imageFile = new File(path + fileName);
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(imageFile);
			BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
			if (bitmap != null) {
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
				bos.flush();
				bos.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 检查手机号是否正确
	 * 
	 * @param phoneNumber
	 *            手机号码
	 * @return 正确返回true 错误返回false
	 */
	public static boolean isTruePhone(String phoneNumber) {
		if (phoneNumber == null || "".equals(phoneNumber)) {
			return false;
		}
		Pattern p = Pattern.compile("^(1[1-9])\\d{9}$");
		Matcher m = p.matcher(phoneNumber);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断邮箱格式是否正确
	 * 
	 * @param emailAddress
	 * @return
	 */
	public static boolean isTrueEmail(String emailAddress) {
		if (emailAddress == null || "".equals(emailAddress)) {
			return false;
		}
		String strPattern = "^[a-zA-Z0-9][ \\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";

		// 利用了Java里面的正则表达式
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(emailAddress);
		// 当目标string与传入的string完全匹配，返回true,否则返回false
		return m.matches();
	}

	/**
	 * 判断输入的是否是数字
	 * 
	 * @param str
	 * @return true 为真 false 为假
	 */
	public static boolean isNum(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		if (str.equals("")) {
			return false;
		}
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	/**
	 * 获取SDCard根路径
	 */
	public static String getSDCardPath() {
		String path = "";
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Tommy/image/";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
		return path;
	}

	/**
	 * 获取路径中的文件名
	 */
	public static String getFileNameFromURL(String url) {
		String fileName = url.substring(url.lastIndexOf("/") + 1);
		return fileName;
	}

	/**
	 * 绘制Gallery亮点
	 * 
	 * @param num
	 * @param position
	 * @param context
	 * @param normalPoint
	 * @param selectedPoint
	 * @param spaceWidth
	 * @return
	 */
	public static Bitmap drawPoint(int num, int position, Context context, int normalPoint, int selectedPoint, int spaceWidth) {
		Bitmap normal = ((BitmapDrawable) context.getResources().getDrawable(normalPoint)).getBitmap();
		Bitmap select = ((BitmapDrawable) context.getResources().getDrawable(selectedPoint)).getBitmap();
		Bitmap bitmap = Bitmap.createBitmap((num + 2) * spaceWidth, spaceWidth / 2, Bitmap.Config.ARGB_8888);

		Canvas mPointsCanvas = new Canvas();
		mPointsCanvas.setBitmap(bitmap);
		int x = spaceWidth;
		for (int i = 0; i < num; i++) {
			if (i == position) {
				mPointsCanvas.drawBitmap(select, x, 0, null);
			} else {
				mPointsCanvas.drawBitmap(normal, x, 0, null);
			}
			x += spaceWidth;
		}
		return bitmap;
	}

	/**
	 * 隐藏键盘
	 * */
	public static void hideKeybord(Activity activity) {
		try {
			((InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
					activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {

		}
	}

	/**
	 * 获取版本号
	 * */
	public static int getVersionCode(Activity activity) {
		int versionCode = 1;
		try {
			PackageInfo info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
			// 当前应用的版本名称
			versionCode = info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * 获得屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	/**
	 * 获得屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}

	/**
	 * 银行卡四位加空格
	 * 
	 * @param mEditText
	 */
	public static void bankCardNumAddSpace(final EditText mEditText) {
		mEditText.addTextChangedListener(new TextWatcher() {
			int beforeTextLength = 0;
			int onTextLength = 0;
			boolean isChanged = false;

			int location = 0;// 记录光标的位置
			private char[] tempChar;
			private StringBuffer buffer = new StringBuffer();
			int konggeNumberB = 0;

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				beforeTextLength = s.length();
				if (buffer.length() > 0) {
					buffer.delete(0, buffer.length());
				}
				konggeNumberB = 0;
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == ' ') {
						konggeNumberB++;
					}
				}
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				onTextLength = s.length();
				buffer.append(s.toString());
				if (onTextLength == beforeTextLength || onTextLength <= 3 || isChanged) {
					isChanged = false;
					return;
				}
				isChanged = true;
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (isChanged) {
					location = mEditText.getSelectionEnd();
					int index = 0;
					while (index < buffer.length()) {
						if (buffer.charAt(index) == ' ') {
							buffer.deleteCharAt(index);
						} else {
							index++;
						}
					}

					index = 0;
					int konggeNumberC = 0;
					while (index < buffer.length()) {
						if ((index == 4 || index == 9 || index == 14 || index == 19)) {
							buffer.insert(index, ' ');
							konggeNumberC++;
						}
						index++;
					}

					if (konggeNumberC > konggeNumberB) {
						location += (konggeNumberC - konggeNumberB);
					}

					tempChar = new char[buffer.length()];
					buffer.getChars(0, buffer.length(), tempChar, 0);
					String str = buffer.toString();
					if (location > str.length()) {
						location = str.length();
					} else if (location < 0) {
						location = 0;
					}
					mEditText.setText(str);
					Editable etable = mEditText.getText();
					Selection.setSelection(etable, location);
					isChanged = false;
				}
			}
		});
	}

	/** ListView与Scrollview的滑动冲突 */
	public static void onListviewRange(ListView listView, final ScrollView scrollView) {
		listView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					scrollView.requestDisallowInterceptTouchEvent(false);
				} else {
					scrollView.requestDisallowInterceptTouchEvent(true);
				}
				return false;
			}
		});
	}

	/** 保留四位小数 */
	public static String formatDouble4(String result) {
		DecimalFormat df = new DecimalFormat("######0.0000");
		return df.format(Double.parseDouble(result));
	}

	/** 保留两位小数 */
	public static String formatDoubleString(double data) {
		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(data);
		// NumberFormat nf = NumberFormat.getNumberInstance();
		// nf.setMaximumFractionDigits(2);
		// return Double.parseDouble(nf.format(data));
	}

	/** 保留两位小数 */
	public static String formatDoubleString(String data) {
		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(Double.parseDouble(data));
		// NumberFormat nf = NumberFormat.getNumberInstance();
		// nf.setMaximumFractionDigits(2);
		// return Double.parseDouble(nf.format(data));
	}

	/** 保留两位小数 */
	public static Double formatDouble(double data) {
		DecimalFormat df = new DecimalFormat("#0.00");
		return Double.parseDouble(df.format(data));
	}

	/** 比较两个时间的先后 */
	public static int compare_date(String DATE1, String DATE2) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date dt1 = (Date) df.parse(DATE1);
			Date dt2 = (Date) df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/** 转换称千分位的数字 */
	public static String Thousandth(String text) {
		DecimalFormat df = null;
		// if(text.indexOf(".") > 0){
		// if(text.length() - text.indexOf(".")-1 == 0){
		// df = new DecimalFormat("###,###,###,##0.00");
		// }else if(text.length() - text.indexOf(".00")-1 == 1){
		// df = new DecimalFormat("###,###,###,##0.00");
		// }else{
		// df = new DecimalFormat("###,###,###,##0.00");
		// }
		// }else{
		df = new DecimalFormat("###,###,###,##0.00");
		// }
		double number = 0.00;
		try {
			number = Double.parseDouble(text);
		} catch (Exception e) {
			number = 0.00;
		}
		if (text.startsWith("-") || text.startsWith("+")) {
			return text.substring(0, 1) + Thousandth(text.substring(1, text.length()));
		}
		return df.format(number);
	}

	/**
	 * 根据需要的 格式去转换
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getPercentFormat(String pattern, double value) {
		DecimalFormat df = null; // 声明一个DecimalFormat类的对象
		df = new DecimalFormat(pattern); // 实例化对象，传入模板
		return df.format(value);
		/***
		 * demo.format1("###,###.###",111222.34567) ;
		 * demo.format1("000,000.000",11222.34567) ;
		 * demo.format1("###,###.###￥",111222.34567) ;
		 * demo.format1("000,000.000￥",11222.34567) ;
		 * demo.format1("##.###%",0.345678) ; demo.format1("00.###%",0.0345678)
		 * ; demo.format1("###.###\u2030",0.345678) ;
		 */
	}

	/***
	 * 动态计算ListView 高度
	 * 
	 * @param adapter
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(BaseAdapter adapter, ListView listView) {
		// 获取ListView对应的Adapter
		adapter = (BaseAdapter) listView.getAdapter();
		if (adapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0, len = adapter.getCount(); i < len; i++) {
			// listAdapter.getCount()返回数据项的数目
			View listItem = adapter.getView(i, null, listView);
			// 计算子项View 的宽高
			listItem.measure(0, 0);
			// 统计所有子项的总高度
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度
		listView.setLayoutParams(params);
	}
}
