package com.xiaoshi.chattingrobot.dapter;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
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
//		TextView tv_artivle = holder.getView(R.id.tv_article);
		holder.setText(R.id.tv_article, t.getArticle());
//		TextView tv_source = holder.getView(R.id.tv_source);
//		tv_artivle.setText(t.getArticle());
//		tv_source.setText("来源:" + t.getSource());
		holder.setText(R.id.tv_source, "来源:" + t.getSource());
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
