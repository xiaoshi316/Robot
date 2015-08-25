package com.xiaoshi.chattingrobot.commadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.xiaoshi.chattingrobot.R;

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
		TextView tv = (TextView) mViews.get(viewId);
		tv.setText(text);
		return this;
	}

	/**
	 * set ImageView by Resource
	 * 
	 * @param viewId
	 * @param resId
	 * @return
	 */
	public ViewHolder setImageViewResource(int viewId, int resId) {
		ImageView iv = (ImageView) mViews.get(viewId);
		iv.setImageResource(resId);
		return this;
	}

	/***
	 * set ImageView by Bitmap
	 * 
	 * @param viewId
	 * @param bitmap
	 * @return
	 */
	public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
		ImageView iv = (ImageView) mViews.get(viewId);
		iv.setImageBitmap(bitmap);
		return this;
	}

	/**
	 * 使用Url加载网络图片
	 * 
	 * @param viewId
	 * @param url
	 * @return
	 */
	public ViewHolder setImageUrl(int viewId, String url) {
		final ImageView iv = (ImageView) mViews.get(viewId);
		DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.normal) // resource
				.showImageForEmptyUri(R.drawable.normal) // resource or
															// drawable
				.showImageOnFail(R.drawable.normal) // resource or
													// drawable
				.resetViewBeforeLoading(false) // default
				.delayBeforeLoading(1000).cacheInMemory(false) // default
				.cacheOnDisk(false) // default
				.considerExifParams(false) // default
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
				.bitmapConfig(Bitmap.Config.ARGB_8888) // default
				.displayer(new SimpleBitmapDisplayer()) // default
				.handler(new Handler()) // default
				.build();
		ImageLoader.getInstance().loadImage(url, options, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				// super.onLoadingComplete(imageUri, view, loadedImage);
				iv.setImageBitmap(loadedImage);
			}
		});
		return this;
	}
}
