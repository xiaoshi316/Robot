package com.xiaoshi.chattingrobot.commadapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;
/***
 * 
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @TODO 公用适配器
 * @2015年8月25日
 * @param <T>
 *
 */
public abstract class CommBaseAdapter<T> extends BaseAdapter {
	protected List<T> mLists;
	protected Context mContext;
	protected LayoutInflater mInflater;
	public CommBaseAdapter(Context context) {
		this.mContext=context;
		mInflater=LayoutInflater.from(context);
	}
	
	public List<T> getmLists() {
		return mLists;
	}

	public void setmLists(List<T> mLists) {
		this.mLists = mLists;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mLists.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return mLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=ViewHolder.getHolder(position, mContext, 
				convertView, parent, getLayoutId(position));
		convert(holder, getItem(position),position);
		return holder.getConvertView();
	}
	/***
	 * 设置布局文件
	 * @return
	 */
	public abstract int getLayoutId(int postion);
	/**
	 * 处理业务
	 * @param holder
	 * @param t
	 */
	public abstract void convert(ViewHolder holder,T t,int position);
	/***
	 * 统一Toast
	 * @param msg
	 */
	public void showToast(String msg){
		Toast.makeText(mContext, msg+"", Toast.LENGTH_SHORT).show();
	};
}
