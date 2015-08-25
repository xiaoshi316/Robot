package com.xiaoshi.chattingrobot.bean;
/***
 * 
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @TODO 菜谱
 * @2015年8月4日
 *
 */
public class Menu {
	private String name;//菜名
	private String info;//详情
	private String detailurl;//详情链接
	private String icon;//图片
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getDetailurl() {
		return detailurl;
	}
	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Override
	public String toString() {
		return "Menu [name=" + name + ", info=" + info + ", detailurl=" + detailurl + ", icon=" + icon + "]";
	}
	
}
