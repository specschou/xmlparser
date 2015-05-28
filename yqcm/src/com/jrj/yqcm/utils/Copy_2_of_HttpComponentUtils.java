package com.jrj.yqcm.utils;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class Copy_2_of_HttpComponentUtils {
	private static ThreadSafeClientConnManager cm;
	private static HttpClient httpclient;
	// static {
	// // HttpConnectionClient≥ı ºªØ
	// try {
	// httpConnectionClient.setCodeing("utf-8");
	// httpConnectionClient.setConnectionMaxPerHost(100);
	// httpConnectionClient.setConnectionMaxTotal(300);
	// httpConnectionClient.setConnectionTimeOut(10000);
	// httpConnectionClient.setSoTimeOut(18000);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	static {

		cm = new ThreadSafeClientConnManager();
		cm.setMaxTotal(20);

		httpclient = new DefaultHttpClient(cm);
	}
	public static void main(String[] args){
		String url = "http://www.sina.com.cn";
		String mainCookie = "";
		get(url,mainCookie);
	}
	public static String get(String url, String mainCookie) {
		System.out.println("url:" + url);
		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("Referer", url);
		httpget.setHeader("Cookie", mainCookie);
		try {
			HttpContext context = new BasicHttpContext();
			System.out.println("execute:" + url);
			HttpResponse response = httpclient.execute(httpget, context);
			System.out.println("execute end:" + url);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				byte[] bytes = EntityUtils.toByteArray(entity);
				Header[] headers = httpget.getAllHeaders();
				System.out.println("header3:");
				for (Header h : headers) {
					System.out.println(h.getName() + ":" + h.getValue() + ",");
				}
				System.out.println("header4:");
				return new String(bytes, "utf-8");
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			httpget.abort();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void login(String url) {
		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("Referer", url);
		try {
			httpclient.execute(httpget);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			httpget.abort();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
