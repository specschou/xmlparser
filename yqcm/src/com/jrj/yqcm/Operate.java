package com.jrj.yqcm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class Operate {
	public static String mainCookie = "Football=UserID=3014BC6652B039DF&UnionID=918AEEBDFAC74ED0&Gender=False&NickName=specschou1";

	public static void main(String[] args) {
		String url = "http://f8.xba.com.cn/ClubOffice/FinancialInspector.aspx?Type=4&timeStamp=1320829268177";
		// url="http://f12.xba.com.cn/NPCMatch/SignForNPCOKNew.aspx?Type=-358";
		// get(url);
		// url = "http://www.xba.com.cn/loginnew.aspx";
		// post(url);
		url = "http://f8.xba.com.cn/AutoLogin.aspx?F=1&U=1283612&S=YTcyMjg3Yjk1ZDA223939223NWMyNjBhMThiZmI0";
		getCookie(url);
		url = "http://f8.xba.com.cn/Task/TaskInterface.aspx?Type=2";
		String content = get(url);
		System.out.println(content);
		int index = 0;
		while (index != -1) {
			index = content.indexOf("PopupTask", index);
			if (index != -1) {
				int begin = content.indexOf(",", index) + 1;
				int end = content.indexOf(",", begin);
				if (begin != -1 && end != -1) {
					System.out.println(content.substring(begin, end));
				}
				index++;
			}
		}
	}

	public static String get(String url) {
		HttpClient hc = new DefaultHttpClient();
		HttpGet hg = new HttpGet(url);
		try {
			hg.addHeader("Cookie", mainCookie);
			String content = hc.execute(hg, new BasicResponseHandler());
			return content;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void getCookie(String url) {
		HttpClient hc = new DefaultHttpClient();
		HttpGet hg = new HttpGet(url);
		try {
			hg.addHeader("Referer", "http://www.xba.com.cn/Membercenter.aspx");
			HttpResponse hr = hc.execute(hg);
			for (Header h : hr.getAllHeaders()) {
				System.out.println(h.getName() + ":" + h.getValue());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void post(String url) {
		HttpClient hc = new DefaultHttpClient();
		HttpPost hp = new HttpPost(url);
		List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
		data.add(new BasicNameValuePair("tbUserName", "specschou"));
		data.add(new BasicNameValuePair("tbPassword", "specschou"));
		data.add(new BasicNameValuePair(
				"__VIEWSTATE",
				"/wEPDwUJODE4MjQ0MDA4D2QWAgIDD2QWAgIDDxYCHgdWaXNpYmxlaGQYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgEFCmxvZ2luX3NhdmXwfpvpqN/Hn4LSMXBpkKJH6OKSvg=="));
		data.add(new BasicNameValuePair("__EVENTVALIDATION",
				"/wEWBQKzh9qNAQLyj/OQAgK3jsrkBAKDvvLdBALZpaCaA0Vq4Wo0t6UY9yeIULt9kFvMGAYC"));
		data.add(new BasicNameValuePair("loginbtn", ""));
		try {
			hp.setEntity(new UrlEncodedFormEntity(data, HTTP.UTF_8));
			hp.addHeader("Host", "www.xba.com.cn");
			hp.addHeader("Connection", "keep-alive");
			hp.addHeader("Cache-Control", "max-age=0");
			hp.addHeader("Origin", "http://www.xba.com.cn");
			hp.addHeader(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.106 Safari/535.2");
			hp.addHeader("Content-Type", "application/x-www-form-urlencoded");
			hp.addHeader("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			hp.addHeader("Referer", "http://www.xba.com.cn/loginnew.aspx");
			hp.addHeader("Accept-Encoding", "gzip,deflate,sdch");
			hp.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
			hp.addHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
			HttpResponse hr = hc.execute(hp);
			HttpEntity he = hr.getEntity();
			System.out.println("response:" + hr.getStatusLine());
			for (Header h : hr.getAllHeaders()) {
				System.out.println(h.getName() + ":" + h.getValue());
				if ("Set-Cookie".equals(h.getName())) {
				}
			}
			if (he != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
