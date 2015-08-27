package com.emotiona.android.dapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.emotiona.android.ContentDetailActivity;
import com.emotiona.android.R;
import com.emotiona.android.bean.ChatMessage;
import com.emotiona.android.bean.Menu;
import com.emotiona.android.bean.News;
import com.emotiona.android.bean.Train;
import com.emotiona.android.bean.ChatMessage.ReturnType;
import com.emotiona.android.bean.ChatMessage.Type;
import com.emotiona.android.commadapter.CommBaseAdapter;
import com.emotiona.android.commadapter.ViewHolder;
import com.emotiona.android.utils.Utils;

/***
 * 
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @TODO 聊天Adapter
 * @2015年7月17日
 *
 */
@SuppressWarnings("rawtypes")
public class SendMsgAdapter extends CommBaseAdapter<ChatMessage> {

	public SendMsgAdapter(Context context) {
		super(context);

	}

	@Override
	public int getLayoutId(int postion) {
		if (getItemViewType(postion) == 0) {
			return R.layout.robot_chat_send_msg;
		}
		return R.layout.robot_chat_from_msg;
	}

	/**
	 * 设置多布局的时候复写getItemViewType 和getViewTypeCount
	 */
	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		ChatMessage chatMessage = mLists.get(position);
		if (chatMessage.getType() == Type.INPUT) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public void convert(ViewHolder holder, final ChatMessage t, int position) {
		// TODO Auto-generated method stub
		if (getItemViewType(position) == 0) {
			TextView chat_send_createDate = holder.getView(R.id.chat_send_createDate);
			chat_send_createDate.setText(t.getDateStr());
			TextView chat_send_content = holder.getView(R.id.chat_send_content);
			chat_send_content.setText(t.getMsg());
		} else {
			TextView chat_send_createDate = holder.getView(R.id.chat_from_createDate);
			TextView chat_send_content = holder.getView(R.id.chat_from_content);
			ListView lv_content = holder.getView(R.id.lv_content);
			if (t.getReturnType() == ReturnType.TEXT) {
				chat_send_content.setText(t.getMsg());
			} else if (t.getReturnType() == ReturnType.LINK) {
				chat_send_content.setText(t.getMsg());
				chat_send_content.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						intent.putExtra("url", t.getMsg());
						intent.setClass(mContext, ContentDetailActivity.class);
						mContext.startActivity(intent);
					}
				});
			} else if (t.getReturnType() == ReturnType.NEWS) {
				chat_send_content.setVisibility(View.GONE);
				lv_content.setVisibility(View.VISIBLE);
				NewsAdapter adapter = new NewsAdapter(mContext);
				adapter.setmLists(t.getListdata());
				lv_content.setAdapter(adapter);
				Utils.setListViewHeightBasedOnChildren(adapter, lv_content);

				lv_content.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						News news = (News) t.getListdata().get((int) id);
						intent.putExtra("url", news.getDetailurl());
						intent.setClass(mContext, ContentDetailActivity.class);
						mContext.startActivity(intent);
					}
				});
			} else if (t.getReturnType() == ReturnType.TRAIN) {
				chat_send_content.setVisibility(View.GONE);
				lv_content.setVisibility(View.VISIBLE);
				TrainAdapter adapter = new TrainAdapter(mContext);
				adapter.setmLists(t.getListdata());
				lv_content.setAdapter(adapter);
				Utils.setListViewHeightBasedOnChildren(adapter, lv_content);
				lv_content.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						Train train = (Train) t.getListdata().get((int) id);
						intent.putExtra("url", train.getDetailurl());
						intent.setClass(mContext, ContentDetailActivity.class);
						mContext.startActivity(intent);
					}
				});
			} else if (t.getReturnType() == ReturnType.FLIGHT) {
				chat_send_content.setVisibility(View.GONE);
				lv_content.setVisibility(View.VISIBLE);
				FlightAdapter adapter = new FlightAdapter(mContext);
				adapter.setmLists(t.getListdata());
				lv_content.setAdapter(adapter);
				Utils.setListViewHeightBasedOnChildren(adapter, lv_content);
				// lv_content.setOnItemClickListener(new OnItemClickListener() {
				// @Override
				// public void onItemClick(AdapterView<?> parent, View view, int
				// position, long id) {
				// // TODO Auto-generated method stub
				// Intent intent =new Intent();
				// Flight train=(Flight) t.getListdata().get((int)id);
				// intent.putExtra("url",train.getDetailurl());
				// intent.setClass(mContext, ContentDetailActivity.class);
				// mContext.startActivity(intent);
				// }
				// });
			} else if (t.getReturnType() == ReturnType.MENU) {
				chat_send_content.setVisibility(View.GONE);
				lv_content.setVisibility(View.VISIBLE);
				MenuAdapter adapter = new MenuAdapter(mContext);
				adapter.setmLists(t.getListdata());
				lv_content.setAdapter(adapter);
				Utils.setListViewHeightBasedOnChildren(adapter, lv_content);
				lv_content.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						Menu train = (Menu) t.getListdata().get((int) id);
						intent.putExtra("url", train.getDetailurl());
						intent.setClass(mContext, ContentDetailActivity.class);
						mContext.startActivity(intent);
					}
				});
			}
			chat_send_createDate.setText(t.getDateStr());
		}
	}

}
