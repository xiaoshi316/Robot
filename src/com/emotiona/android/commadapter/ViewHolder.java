package com.emotiona.android.commadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/***
 * 
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @TODO 复用控件类
 * @2015年7月14日
 *
 */
public class ViewHolder {
	private SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;
	private Context mContext;

	/***
	 * 构造方法
	 * 
	 * @param position
	 * @param context
	 * @param parent
	 * @param layoutId
	 */
	public ViewHolder(int position, Context context, ViewGroup parent, int layoutId) {
		this.mViews = new SparseArray<View>();
		this.mPosition = position;
		this.mContext = context;
		this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		mConvertView.setTag(this);
	}

	/**
	 * 获取ViewHolder
	 * 
	 * @param position
	 * @param context
	 * @param convertView
	 * @param parent
	 * @param layoutId
	 * @return
	 */
	public static ViewHolder getHolder(int position, Context context, View convertView, ViewGroup parent, int layoutId) {
		if (convertView == null) {
			return new ViewHolder(position, context, parent, layoutId);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}

	public View getConvertView() {

		return mConvertView;
	}

	/***
	 * 根据id获取控件
	 * 
	 * @param viewId
	 * @return
	 */
	public <T extends View> T getView(int viewId) {
		return retrieveView(viewId);
	}

	@SuppressWarnings("unchecked")
	protected <T extends View> T retrieveView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	/**
	 * 设置TextView文本
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setText(int viewId, String text) {
		TextView tv = (TextView) retrieveView(viewId);
		tv.setText(text);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param color
	 * @return
	 * @2015年8月26日下午2:38:29
	 * @TODO 设置字体颜色
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setTextColor(int viewId, int color) {
		TextView tv = (TextView) retrieveView(viewId);
		tv.setTextColor(color);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param typeface
	 * @return
	 * @2015年8月26日下午3:07:32
	 * @TODO 设置TextView 字体
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setTextTypeface(int viewId, Typeface typeface) {
		TextView view = retrieveView(viewId);
		view.setTypeface(typeface);
		view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param resId
	 * @return
	 * @2015年8月26日下午3:08:05
	 * @TODO 根据资源ID设置图片
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setImageViewResource(int viewId, int resId) {
		ImageView iv = (ImageView) retrieveView(viewId);
		iv.setImageResource(resId);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param bitmap
	 * @return
	 * @2015年8月26日下午3:08:30
	 * @TODO 根据Bitmap 设置图片
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
		ImageView iv = (ImageView) retrieveView(viewId);
		iv.setImageBitmap(bitmap);
		return this;
	}

	/**
	 * 
	 * @param viewId
	 * @param url
	 * @return
	 * @2015年8月26日下午3:09:17
	 * @TODO 根据URL加载图片
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setImageUrl(int viewId, String url) {
		final ImageView iv = (ImageView) retrieveView(viewId);
		// Picasso.with(mContext).load(url).into(iv);
		// 图像处理
		Picasso.with(mContext).load(url).resize(50, 50)//
				.centerCrop().into(iv);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param requestBuilder
	 * @return
	 * @2015年8月26日下午2:42:38
	 * @TODO 自定义Picasso 设置图片 根据用户需要来
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setImageBuilder(int viewId, RequestCreator requestBuilder) {
		ImageView view = (ImageView) retrieveView(viewId);
		requestBuilder.into(view);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param value
	 * @return
	 * @2015年8月26日下午2:47:58
	 * @TODO 给View 设置动画
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	@SuppressLint("NewApi")
	public ViewHolder setAlpha(int viewId, float value) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			retrieveView(viewId).setAlpha(value);
		} else {
			AlphaAnimation alpha = new AlphaAnimation(value, value);
			alpha.setDuration(0);
			alpha.setFillAfter(true);
			retrieveView(viewId).startAnimation(alpha);
		}
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param visible
	 * @return
	 * @2015年8月26日下午2:51:48
	 * @TODO 设置View是否显示
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setVisible(int viewId, Boolean visible) {
		View view = retrieveView(viewId);
		view.setVisibility(visible ? View.VISIBLE : View.GONE);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @return
	 * @2015年8月26日下午3:01:50
	 * @TODO 添加超链接
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder linkify(int viewId) {
		TextView view = retrieveView(viewId);
		Linkify.addLinks(view, Linkify.ALL);
		return this;
	}

	/**
	 * 
	 * @param viewId
	 * @param progress
	 * @return
	 * @2015年8月26日下午3:10:07
	 * @TODO 设置进度条
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setProgress(int viewId, int progress) {
		ProgressBar view = retrieveView(viewId);
		view.setProgress(progress);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param progress
	 * @param max
	 * @return
	 * @2015年8月26日下午3:10:40
	 * @TODO 设置进度条
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setProgress(int viewId, int progress, int max) {
		ProgressBar view = retrieveView(viewId);
		view.setMax(max);
		view.setProgress(progress);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param max
	 * @return
	 * @2015年8月26日下午3:11:22
	 * @TODO 设置进度条
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setMax(int viewId, int max) {
		ProgressBar view = retrieveView(viewId);
		view.setMax(max);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param rating
	 * @return
	 * @2015年8月26日下午3:12:03
	 * @TODO 设置星星
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setRating(int viewId, float rating) {
		RatingBar view = retrieveView(viewId);
		view.setRating(rating);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param rating
	 * @param max
	 * @return
	 * @2015年8月26日下午3:12:35
	 * @TODO 用一句话来描述此功能的用途
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setRating(int viewId, float rating, int max) {
		RatingBar view = retrieveView(viewId);
		view.setMax(max);
		view.setRating(rating);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param tag
	 * @return
	 * @2015年8月26日下午3:13:09
	 * @TODO 给View设置tag
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setTag(int viewId, Object tag) {
		View view = retrieveView(viewId);
		view.setTag(tag);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param key
	 * @param tag
	 * @return
	 * @2015年8月26日下午3:13:47
	 * @TODO 给View设置tag
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setTag(int viewId, int key, Object tag) {
		View view = retrieveView(viewId);
		view.setTag(key, tag);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param checked
	 * @return
	 * @2015年8月26日下午3:14:35
	 * @TODO 设置复选框
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setChecked(int viewId, boolean checked) {
		Checkable view = (Checkable) retrieveView(viewId);
		view.setChecked(checked);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param adapter
	 * @return
	 * @2015年8月26日下午3:15:39
	 * @TODO 设置Adapter
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setAdapter(int viewId, Adapter adapter) {
		AdapterView view = retrieveView(viewId);
		view.setAdapter(adapter);
		return this;
	}

	/**
	 * 
	 * @param viewId
	 * @param listener
	 * @return
	 * @2015年8月26日下午3:16:12
	 * @TODO 设置点击事件
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
		View view = retrieveView(viewId);
		view.setOnClickListener(listener);
		return this;
	}

	/**
	 * 
	 * @param viewId
	 * @param listener
	 * @return
	 * @2015年8月26日下午3:16:37
	 * @TODO 设置触摸事件
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
		View view = retrieveView(viewId);
		view.setOnTouchListener(listener);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param listener
	 * @return
	 * @2015年8月26日下午3:17:13
	 * @TODO 设置长安事件
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
		View view = retrieveView(viewId);
		view.setOnLongClickListener(listener);
		return this;
	}

	/***
	 * 
	 * @param viewId
	 * @param color
	 * @return
	 * @2015年8月26日下午2:33:26
	 * @TODO 设置背景颜色
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public ViewHolder setBackgroundColor(int viewId, int color) {
		View view = retrieveView(viewId);
		view.setBackgroundColor(color);
		return this;
	}

}
