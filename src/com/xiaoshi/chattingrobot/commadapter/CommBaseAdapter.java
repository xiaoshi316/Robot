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
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
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
		ViewHolder holder = ViewHolder.getHolder(position, mContext, convertView, parent, getLayoutId(position));
		convert(holder, getItem(position), position);
		return holder.getConvertView();
	}

	/***
	 * 设置布局文件
	 * 
	 * @return
	 */
	public abstract int getLayoutId(int postion);

	/**
	 * 处理业务
	 * 
	 * @param holder
	 * @param t
	 */
	public abstract void convert(ViewHolder holder, T t, int position);

	/***
	 * 统一Toast
	 * 
	 * @param msg
	 */
	public void showToast(String msg) {
		Toast.makeText(mContext, msg + "", Toast.LENGTH_SHORT).show();
	};

	@Override
	public boolean isEnabled(int position) {
		return position < mLists.size();
	}
	/***
	 * 
	 * @param elem
	 * @2015年8月26日下午3:34:12
	 * @TODO 添加一个t
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public void add(T elem) {
		mLists.add(elem);
		notifyDataSetChanged();
	}
	/***
	 * 
	 * @param elem
	 * @2015年8月26日下午3:33:41
	 * @TODO 添加数据List<T> elem
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public void addAll(List<T> elem) {
		mLists.addAll(elem);
		notifyDataSetChanged();
	}
	/***
	 * 
	 * @param oldElem
	 * @param newElem
	 * @2015年8月26日下午3:33:29
	 * @TODO 改变实体
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public void set(T oldElem, T newElem) {
		set(mLists.indexOf(oldElem), newElem);
	}
	/***
	 * 
	 * @param index
	 * @param elem
	 * @2015年8月26日下午3:33:02
	 * @TODO 根据Index改变实体
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public void set(int index, T elem) {
		mLists.set(index, elem);
		notifyDataSetChanged();
	}
	/***
	 * 
	 * @param elem
	 * @2015年8月26日下午3:32:46
	 * @TODO 删除某个实体对象
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public void remove(T elem) {
		mLists.remove(elem);
		notifyDataSetChanged();
	}
	/**
	 * 
	 * @param index
	 * @2015年8月26日下午3:32:24
	 * @TODO 根据实体ID删除
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public void remove(int index) {
		mLists.remove(index);
		notifyDataSetChanged();
	}
	/**
	 * 
	 * @param elem
	 * @2015年8月26日下午3:32:09
	 * @TODO 替换数据
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public void replaceAll(List<T> elem) {
		mLists.clear();
		mLists.addAll(elem);
		notifyDataSetChanged();
	}
	/**
	 * 
	 * @param elem
	 * @return
	 * @2015年8月26日下午3:31:24
	 * @TODO 是否包含某个T
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public boolean contains(T elem) {
		return mLists.contains(elem);
	}

	/***
	 * 
	 * 
	 * @2015年8月26日下午3:31:41
	 * @TODO Clear mLists list
	 * @author emotiona
	 * @Email emotiona_xiaoshi@126.com
	 */
	public void clear() {
		mLists.clear();
		notifyDataSetChanged();
	}
}
