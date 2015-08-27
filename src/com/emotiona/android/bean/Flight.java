package com.emotiona.android.bean;
/***
 * 
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @TODO 航班实体类
 * @2015年8月4日
 *
 */
public class Flight {
	private String flight;//航班
	private String route;//航班路线
	private String  starttime;//起飞时间
	private String endtime;//到达时间
	private String state;//航班状态
	private String detailurl;//详情
	private String icon;//图片地址
	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
		return "Flight [flight=" + flight + ", route=" + route + ", starttime=" + starttime + ", endtime=" + endtime + ", state=" + state + ", detailurl="
				+ detailurl + ", icon=" + icon + "]";
	}
}
