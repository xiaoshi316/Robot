package com.emotiona.android;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.emotiona.android.R;
import com.emotiona.android.base.BaseActivity;
import com.emotiona.android.bean.ChatMessage;
import com.emotiona.android.bean.Flight;
import com.emotiona.android.bean.Menu;
import com.emotiona.android.bean.News;
import com.emotiona.android.bean.Train;
import com.emotiona.android.bean.ChatMessage.ReturnType;
import com.emotiona.android.bean.ChatMessage.Type;
import com.emotiona.android.dapter.SendMsgAdapter;
import com.emotiona.android.exception.NotKeyExpection;
import com.emotiona.android.fastjson.Helper;
import com.emotiona.android.fastjson.JsonResultUtils;
import com.emotiona.android.fastjson.JsonUtils;
import com.emotiona.android.http.ICallBack;
import com.emotiona.android.http.NetHelper;
import com.emotiona.android.logutils.LogUtils;

public class MainActivity extends BaseActivity {
	private Button btn_chat_send;
	private EditText et_chat_msg;
	private ListView lv_chat;
	public String APIKEY = "dfa2fa0abdf9c8167261a883c20c9231";
	private List<ChatMessage> lists;
	private SendMsgAdapter adapter;

	@Override
	protected void initPageView() {
		// TODO Auto-generated method stub
		btn_chat_send = (Button) super.findViewById(R.id.btn_chat_send);
		et_chat_msg = (EditText) super.findViewById(R.id.et_chat_msg);
		lv_chat = (ListView) super.findViewById(R.id.lv_chat);
		lists = new ArrayList<ChatMessage>();
		adapter = new SendMsgAdapter(this);
	}

	@Override
	protected void initPageViewListener() {
		// TODO Auto-generated method stub
		btn_chat_send.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String msg = et_chat_msg.getText().toString();
				if (TextUtils.isEmpty(msg)) {
					showToast("发送消息不能为空");
					return;
				} else {
					ChatMessage chatMessage = new ChatMessage();
					chatMessage.setDate(new Date());
					chatMessage.setMsg(msg);
					chatMessage.setType(Type.INPUT);
					lists.add(chatMessage);
					adapter.setmLists(lists);
					lv_chat.setAdapter(adapter);
					sendMessage(msg);
				}
				close();
			}
		});
	}

	/***
	 * 
	 * @param msg
	 */
	public void sendMessage(String msg) {
		String INFO = "";
		try {
			INFO = URLEncoder.encode(msg, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		et_chat_msg.setText("");
		String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;
		new NetHelper().getResult(getURL, new ICallBack() {
			@Override
			public void onSuccess(String response) {
				// {"code":100000,"text":"这对于纯洁的我来说实在是 捂脸"} response
				Helper jsonhelper = JsonResultUtils.helper(response);
				try {
					if (jsonhelper.getCode().equals("100000")) {
						String content = jsonhelper.getText();
						ChatMessage chatMessage = new ChatMessage();
						chatMessage.setDate(new Date());
						chatMessage.setMsg(content);
						chatMessage.setType(Type.OUTPUT);
						chatMessage.setReturnType(ReturnType.TEXT);
						lists.add(chatMessage);
					} else if (jsonhelper.getCode().equals("200000")) {
						String url = jsonhelper.getUrl();
						ChatMessage chatMessage = new ChatMessage();
						chatMessage.setDate(new Date());
						chatMessage.setMsg(url);
						chatMessage.setType(Type.OUTPUT);
						chatMessage.setReturnType(ReturnType.LINK);
						lists.add(chatMessage);
					} else if (jsonhelper.getCode().equals("302000")) {
						String list = jsonhelper.getList();
						List<News> mlists = JsonUtils.getObjects(list, News.class);
						ChatMessage<News> chatMessage = new ChatMessage<News>();
						chatMessage.setDate(new Date());
						chatMessage.setType(Type.OUTPUT);
						chatMessage.setListdata(mlists);
						chatMessage.setReturnType(ReturnType.NEWS);
						lists.add(chatMessage);
					} else if (jsonhelper.getCode().equals("305000")) {
						String list = jsonhelper.getList();
						List<Train> mlists = JsonUtils.getObjects(list, Train.class);
						ChatMessage<Train> chatMessage = new ChatMessage<Train>();
						chatMessage.setDate(new Date());
						chatMessage.setType(Type.OUTPUT);
						chatMessage.setListdata(mlists);
						chatMessage.setReturnType(ReturnType.TRAIN);
						lists.add(chatMessage);
					} else if (jsonhelper.getCode().equals("306000")) {
						String list = jsonhelper.getList();
						List<Flight> mlists = JsonUtils.getObjects(list, Flight.class);
						ChatMessage<Flight> chatMessage = new ChatMessage<Flight>();
						chatMessage.setDate(new Date());
						chatMessage.setType(Type.OUTPUT);
						chatMessage.setListdata(mlists);
						chatMessage.setReturnType(ReturnType.FLIGHT);
						lists.add(chatMessage);
					} else if (jsonhelper.getCode().equals("308000")) {
						String list = jsonhelper.getList();
						List<Menu> mlists = JsonUtils.getObjects(list, Menu.class);
						ChatMessage<Menu> chatMessage = new ChatMessage<Menu>();
						chatMessage.setDate(new Date());
						chatMessage.setType(Type.OUTPUT);
						chatMessage.setListdata(mlists);
						chatMessage.setReturnType(ReturnType.MENU);
						lists.add(chatMessage);
					}
				} catch (NotKeyExpection e) {
					e.printStackTrace();
				}
				adapter.setmLists(lists);
				adapter.notifyDataSetChanged();
				lv_chat.setSelection(lists.size());
			}

			@Override
			public void onFailed(String error) {

			}
		},false,null,this);
	}

	@Override
	protected int initPageLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.robot_chatting;
	}

	@Override
	protected void process(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//获取appaction下面的meta_name节点
		 ApplicationInfo appInfo;
		try {
			appInfo = this.getPackageManager()
			            .getApplicationInfo(getPackageName(),PackageManager.GET_META_DATA);
			String meta_name=appInfo.metaData.getString("meta_name");
			LogUtils.e(meta_name);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/***
	 * 点击输入框的时候关闭输入法和上啦布局
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		close();
		return super.onTouchEvent(event);
	}

	private void close() {
		InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(et_chat_msg.getWindowToken(), 0);
	}
}
