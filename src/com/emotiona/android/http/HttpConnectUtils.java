package com.emotiona.android.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import com.emotiona.android.utils.InputStreamToString;

import android.text.TextUtils;
import android.util.Log;


public class HttpConnectUtils {
	private static InputStream is = null;

	private static final String CHARSET = HTTP.UTF_8;
	private static DefaultHttpClient customerHttpClient;

	/**
	 * 单利模式获取HttpClient 同步
	 * 
	 * @return
	 */
	private static DefaultHttpClient getHttpClient() {
		if (null == customerHttpClient) {
			HttpParams params = new BasicHttpParams();
			// 设置一些基本参数
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, CHARSET);
			HttpProtocolParams.setUseExpectContinue(params, true);
			HttpProtocolParams.setUserAgent(params, "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) "
					+ "AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
			// 超时设置
			/* 从连接池中取连接的超时时间 */
			ConnManagerParams.setTimeout(params, 1000);
			/* 连接超时 */
			HttpConnectionParams.setConnectionTimeout(params, 2000);
			/* 请求超时 */
			HttpConnectionParams.setSoTimeout(params, 4000);

			// 设置我们的HttpClient支持HTTP和HTTPS两种模式
			SchemeRegistry schReg = new SchemeRegistry();
			schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

			// 使用线程安全的连接管理来创建HttpClient
			ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);
			customerHttpClient = new DefaultHttpClient(conMgr, params);
		}
		return customerHttpClient;
	}

	/**
	 * 从服务器通过get方式获取数据 返回的String
	 * 
	 * @param path
	 *            路径
	 * @return String
	 * @throws IOException
	 * @throws Exception
	 * @throws Exception
	 */
	public static String getClient(String path) throws Exception {
		String string = null;
		Log.e("url", path);
		DefaultHttpClient client = (DefaultHttpClient) HttpClientHelper.getClient();
		HttpGet request = new HttpGet(path);
//		if (!MyAppaction.isLogin) {
//			request.setHeader("Cookie", MyAppaction.sharePreferences.readString("cookie", null));
//		}
		HttpResponse response;
		try {
			response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				is = response.getEntity().getContent();
				long length = response.getEntity().getContentLength();
				string = InputStreamToString.MyIsToString(is);
				Log.e("response", string);
//				if (MyAppaction.isLogin) {// 只有当为登录的时候才用
//					getCookie(client);
//				}
//				MyAppaction.isLogin = false;
				return string;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}
		return string;
	}

	/**
	 * 从服务器通过post方式提交数据
	 * 
	 * @param path
	 * @param param
	 *            参数
	 * @return String
	 * @throws Exception
	 */
	public static String postClient(String path, Map<String, String> paramMap) throws Exception {
		String string = null;
		DefaultHttpClient client = (DefaultHttpClient) HttpClientHelper.getClient();
		HttpPost request = new HttpPost(path);
//		if (MyAppaction.isLogin) {// 当进行的操作不是登录的时候，给请求设置Cookie
//			request.setHeader("Cookie", MyAppaction.sharePreferences.readString("cookie", ""));
//			Log.i("cookie", MyAppaction.sharePreferences.readString("cookie", ""));
//
//		}
		Log.e("url", path);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>(paramMap.size());
		if (paramMap != null) {
			for (Map.Entry<String, String> entry : paramMap.entrySet()) {
				NameValuePair nvp = new BasicNameValuePair(entry.getKey(), entry.getValue());
				nvps.add(nvp);
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, "utf-8");
			request.setEntity(entity);
		}
		HttpResponse response;
		try {
			response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				is = response.getEntity().getContent();
				long length = response.getEntity().getContentLength();
				string = InputStreamToString.MyIsToString(is);
//				if (!MyAppaction.isLogin) {// 只有当为登录的时候才用
//					getCookie(client);// 当操作是用户登录的时候记录Cookie
//					MyAppaction.isLogin = true;
//				}
				Log.e("response",string);
				return string;
			} else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_GATEWAY_TIMEOUT) {
				return "请求超时";
			} else {
				return "链接错误";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}
		return string;
	}

	/**
	 * 存储Cookie
	 * 
	 * @param httpClient
	 */
	private static void getCookie(DefaultHttpClient httpClient) {
		List<Cookie> cookies = httpClient.getCookieStore().getCookies();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cookies.size(); i++) {
			Cookie cookie = cookies.get(i);
			String cookieName = cookie.getName();
			String cookieValue = cookie.getValue();
			if (!TextUtils.isEmpty(cookieName) && !TextUtils.isEmpty(cookieValue)) {
				sb.append(cookieName + "=");
				sb.append(cookieValue + ";");
			}
		}
	}
}
