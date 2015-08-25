package com.xiaoshi.chattingrobot.bean;
/***
 * 新闻实体类
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @TODO
 * @2015年8月4日
 *
 */
public class News {
	private String article;
	private String source;
	private String detailurl;
	private String icon;
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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
		return "News [article=" + article + ", source=" + source + ", detailurl=" + detailurl + ", icon=" + icon + "]";
	}
}
