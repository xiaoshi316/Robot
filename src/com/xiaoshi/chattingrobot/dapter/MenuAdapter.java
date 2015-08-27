package com.xiaoshi.chattingrobot.dapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiaoshi.chattingrobot.R;
import com.xiaoshi.chattingrobot.bean.Menu;
import com.xiaoshi.chattingrobot.commadapter.CommBaseAdapter;
import com.xiaoshi.chattingrobot.commadapter.ViewHolder;

public class MenuAdapter extends CommBaseAdapter<Menu> {

	public MenuAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getLayoutId(int postion) {
		// TODO Auto-generated method stub
		return R.layout.item_train;
	}

	@Override
	public void convert(ViewHolder holder, Menu t, int position) {
		// TODO Auto-generated method stub
		ImageView iv_icon = holder.getView(R.id.iv_icon);
		setImageUrl(iv_icon, t.getIcon());
		TextView tv_trainnum = holder.getView(R.id.tv_trainnum);
		TextView tv_start_end = holder.getView(R.id.tv_start_end);
		TextView tv_start_end_time = holder.getView(R.id.tv_start_end_time);
		tv_trainnum.setText(t.getName());
		tv_start_end.setText(t.getInfo());
		tv_start_end_time.setText("");
	}

	/**
	 * 设置图片
	 * 
	 * @param iv
	 * @param url
	 */
	public void setImageUrl(final ImageView iv, final String url) {
		Picasso.with(mContext).load(url).into(iv);
	}
}
