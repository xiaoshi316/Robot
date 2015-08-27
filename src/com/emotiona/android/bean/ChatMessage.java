package com.emotiona.android.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatMessage<T>{

	/**
	 * 输入还是输出
	 */
	private Type type;
	/**
	 * 消息
	 */
	private String msg;
	/**
	 * 日期
	 */
	private Date date;
	/**
	 * 日期字符
	 */
	private String dateStr;
	/**
	 * 名字
	 */
	private String name;
	
	private ReturnType returnType;


	private List<T> listdata;

	/**
	 * 聊天类型，
	 * @author xiaoshi email:emotiona_xiaoshi@126.com
	 * @TODO 输入还是输出
	 * @2015年8月4日
	 *
	 */
	public enum Type {
		INPUT, OUTPUT 
	}
	/**
	 * 返回类型
	 * @author xiaoshi email:emotiona_xiaoshi@126.com
	 * @TODO 文字，链接，新闻，火车，航班，菜谱
	 * @2015年8月4日
	 *
	 */
	public enum ReturnType{
		TEXT,LINK,NEWS,TRAIN,FLIGHT,MENU 
	}
	public ChatMessage() {
	}

	public ChatMessage(Type type, String msg) {
		super();
		this.type = type;
		this.msg = msg;
		setDate(new Date());
	}

	public String getDateStr() {
		return dateStr;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.dateStr = df.format(date);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(ChatMessage.Type input) {
		this.type = input;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public List<T> getListdata() {
		return listdata;
	}

	public void setListdata(List<T> listdata) {
		this.listdata = listdata;
	}

	public ReturnType getReturnType() {
		return returnType;
	}

	public void setReturnType(ReturnType returnType) {
		this.returnType = returnType;
	}
	@Override
	public String toString() {
		return "ChatMessage [type=" + type + ", msg=" + msg + ", date=" + date + ", dateStr=" + dateStr + ", name=" + name + ", listdata=" + listdata + "]";
	}

}
