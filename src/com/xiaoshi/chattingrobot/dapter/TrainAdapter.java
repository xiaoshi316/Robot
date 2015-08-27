package com.xiaoshi.chattingrobot.dapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiaoshi.chattingrobot.R;
import com.xiaoshi.chattingrobot.bean.Train;
import com.xiaoshi.chattingrobot.commadapter.CommBaseAdapter;
import com.xiaoshi.chattingrobot.commadapter.ViewHolder;

public class TrainAdapter extends CommBaseAdapter<Train> {

	public TrainAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getLayoutId(int postion) {
		// TODO Auto-generated method stub
		return R.layout.item_train;
	}

	@Override
	public void convert(ViewHolder holder, Train t, int position) {
		// TODO Auto-generated method stub
		ImageView iv_icon = holder.getView(R.id.iv_icon);
		setImageUrl(iv_icon, t.getIcon());
		TextView tv_trainnum = holder.getView(R.id.tv_trainnum);
		TextView tv_start_end = holder.getView(R.id.tv_start_end);
		TextView tv_start_end_time = holder.getView(R.id.tv_start_end_time);
		tv_trainnum.setText(t.getTrainnum());
		tv_start_end.setText(t.getStart() + "→" + t.getTerminal());
		tv_start_end_time.setText(t.getStarttime() + "----" + t.getEndtime());
	}

	/**
	 * 设置图片
	 * 
	 * @param iv
	 * @param url
	 */
	public void setImageUrl(final ImageView iv, final String url) {
//		DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.normal) // resource
//				.showImageForEmptyUri(R.drawable.normal) // resource or
//				.showImageOnFail(R.drawable.normal) // resource or
//				.resetViewBeforeLoading(false) // default
//				.delayBeforeLoading(1000).cacheInMemory(false) // default
//				.cacheOnDisk(false) // default
//				.considerExifParams(false) // default
//				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
//				.bitmapConfig(Bitmap.Config.RGB_565) // default
//				.displayer(new SimpleBitmapDisplayer()) // default
//				.handler(new Handler()) // default
//				.build();
//		ImageLoader.getInstance().loadImage("http://img3.cache.netease.com/3g/2015/8/4/20150804134737557fa.jpg", options, new SimpleImageLoadingListener() {
//			@Override
//			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//				super.onLoadingComplete(imageUri, view, loadedImage);
//				iv.setImageBitmap(loadedImage);
//			}
//		});
		Picasso.with(mContext).load(url).into(iv);
	}
}
