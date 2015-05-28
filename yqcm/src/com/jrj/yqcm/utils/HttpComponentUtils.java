package com.jrj.yqcm.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpComponentUtils {
	private static Map<String, DefaultHttpClient> hcMap = new HashMap<String, DefaultHttpClient>();

	public static DefaultHttpClient getHc(String mainCookie) {
		if (hcMap.get(mainCookie) == null) {
			ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager();
			cm.setMaxTotal(4);

			DefaultHttpClient httpclient = new DefaultHttpClient(cm);
			DefaultHttpParams.getDefaultParams().setParameter(
					"http.protocol.cookie-policy",
					CookiePolicy.BROWSER_COMPATIBILITY);
			hcMap.put(mainCookie, httpclient);
		}
		return hcMap.get(mainCookie);
	}

	public static void main(String[] args) {
		String url = "http://www.sina.com.cn";
		String mainCookie = "";
		get(url, mainCookie);
		get(url, mainCookie);
		get(url, mainCookie);
	}

	public static String getWeb(String url, String cookie, String football) {
		// System.out.println("url:" + url);
		DefaultHttpClient httpclient = getHc("default");
		HttpGet httpget = new HttpGet(url);
		cookie = cookie + football;
		httpget.setHeader("Referer", url);
		httpget.setHeader("Cookie", cookie);
		httpget.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpget.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
		try {
			HttpContext context = new BasicHttpContext();
			// System.out.println("execute:" + url);
			HttpResponse response = httpclient.execute(httpget, context);
			// System.out.println("execute end:" + url);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				byte[] bytes = EntityUtils.toByteArray(entity);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return new String(bytes, "utf-8");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpget.abort();
		}
		return null;
	}

	public static String get(String url, String mainCookie) {
		// System.out.println("url:" + url);
		DefaultHttpClient httpclient = getHc(mainCookie);
		HttpGet httpget = new HttpGet(url);
		String football = Config.list.get(Integer.parseInt(mainCookie))
				.getProperty("Football");
		String cookie = Config.list.get(Integer.parseInt(mainCookie))
				.getProperty("cookie");
		cookie = cookie + football;
		httpget.setHeader("Referer", url);
		httpget.setHeader("Cookie", cookie);
		// System.out.println("football:" + football);
		// System.out.println("cookie:" + cookie);
		// httpget.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
		// httpget.setHeader("Accept-Encoding", "gzip, deflate");
		// httpget.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		httpget.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpget.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
		try {
			HttpContext context = new BasicHttpContext();
			// System.out.println("execute:" + url);
			HttpResponse response = httpclient.execute(httpget, context);
			// System.out.println("execute end:" + url);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				byte[] bytes = EntityUtils.toByteArray(entity);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return new String(bytes, "utf-8");
				// return new String(bytes);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			httpget.abort();
		}
		return null;
	}

	public static void login(String url, String itemNum, boolean setCookie) {
		DefaultHttpClient httpclient = getHc(itemNum);
		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("Referer", url);
		// httpget.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
		// httpget.setHeader("Accept-Encoding", "gzip, deflate");
		// httpget.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		httpget.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpget.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
		try {
			httpclient.execute(httpget);
			Properties p = Config.list.get(Integer.parseInt(itemNum));
			if (setCookie || p.getProperty("Football") == null) {
				CookieStore cookieStore = httpclient.getCookieStore();
				List<Cookie> cookies = cookieStore.getCookies();
				for (Cookie cookie : cookies) {
					// System.out.println(itemNum + ",cookie222:"
					// +cookie.getName()
					// + "," + cookie.getValue());
					if ("Football".equals(cookie.getName())) {
						p.setProperty("Football", cookie.getName() + "="
								+ cookie.getValue() + ";");
					}
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			httpget.abort();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void login(String url) {
		DefaultHttpClient httpclient = getHc("default");
		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("Referer", url);
		httpget.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpget.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
		try {
			httpclient.execute(httpget);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			httpget.abort();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void login(String url, String itemNum) {
		login(url, itemNum, false);
	}
}
