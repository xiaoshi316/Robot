package com.xiaoshi.chattingrobot.dapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.xiaoshi.chattingrobot.R;
import com.xiaoshi.chattingrobot.bean.News;
import com.xiaoshi.chattingrobot.commadapter.CommBaseAdapter;
import com.xiaoshi.chattingrobot.commadapter.ViewHolder;

public class NewsAdapter extends CommBaseAdapter<News> {

	public NewsAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getLayoutId(int postion) {
		// TODO Auto-generated method stub
		return R.layout.item_news;
	}

	@Override
	public void convert(ViewHolder holder, News t, int position) {
		// TODO Auto-generated method stub
		ImageView iv_icon = holder.getView(R.id.iv_icon);
		setImageUrl(iv_icon, t.getIcon());
		TextView tv_artivle = holder.getView(R.id.tv_article);
		TextView tv_source = holder.getView(R.id.tv_source);
		tv_artivle.setText(t.getArticle());
		tv_source.setText("来源:" + t.getSource());
	}

	/**
	 * 设置图片
	 * 
	 * @param iv
	 * @param url
	 */
	public void setImageUrl(final ImageView iv, final String url) {
		DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.normal) // resource
				.showImageForEmptyUri(R.drawable.normal) // resource or
				.showImageOnFail(R.drawable.normal) // resource or
				.resetViewBeforeLoading(false) // default
				.delayBeforeLoading(1000).cacheInMemory(false) // default
				.cacheOnDisk(false) // default
				.considerExifParams(false) // default
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
				.bitmapConfig(Bitmap.Config.RGB_565) // default
				.displayer(new SimpleBitmapDisplayer()) // default
				.handler(new Handler()) // default
				.build();
		ImageLoader.getInstance().loadImage("http://img3.cache.netease.com/3g/2015/8/4/20150804134737557fa.jpg", options, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				super.onLoadingComplete(imageUri, view, loadedImage);
				iv.setImageBitmap(loadedImage);
			}
		});
	}
}
