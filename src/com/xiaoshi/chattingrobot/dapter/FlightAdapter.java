package com.xiaoshi.chattingrobot.dapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiaoshi.chattingrobot.R;
import com.xiaoshi.chattingrobot.bean.Flight;
import com.xiaoshi.chattingrobot.commadapter.CommBaseAdapter;
import com.xiaoshi.chattingrobot.commadapter.ViewHolder;

public class FlightAdapter extends CommBaseAdapter<Flight> {

	public FlightAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getLayoutId(int postion) {
		// TODO Auto-generated method stub
		return R.layout.item_train;
	}

	@Override
	public void convert(ViewHolder holder, Flight t, int position) {
		// TODO Auto-generated method stub
		ImageView iv_icon = holder.getView(R.id.iv_icon);
		setImageUrl(iv_icon, t.getIcon());
		TextView tv_trainnum = holder.getView(R.id.tv_trainnum);
		TextView tv_start_end = holder.getView(R.id.tv_start_end);
		TextView tv_start_end_time = holder.getView(R.id.tv_start_end_time);
		tv_trainnum.setText(" ");
		tv_start_end.setText(t.getFlight());
		tv_start_end_time.setText(t.getStarttime() + t.getEndtime());
	}

	/**
	 * 设置图片
	 * 
	 * @param iv
	 * @param url
	 */
	public void setImageUrl(final ImageView iv, final String url) {
		Picasso.with(mContext).load(url).into(iv);
		//图像处理
//		Picasso.with(context)
//		  .load(url)
//		  .resize(50, 50)//
//		  .centerCrop()
//		  .into(imageView)
	};
}
