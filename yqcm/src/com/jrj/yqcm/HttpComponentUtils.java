package com.jrj.yqcm;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpComponentUtils {
	public static String get(String url, String mainCookie) {
		HttpClient hc = new DefaultHttpClient();
		HttpGet hg = new HttpGet(url);
		try {
			hg.addHeader("Cookie", mainCookie);
			return hc.execute(hg, new BasicResponseHandler());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void login(String url) {
		HttpClient hc = new DefaultHttpClient();
		HttpGet hg = new HttpGet(url);
		try {
			hg.addHeader("Referer", "http://www.xba.com.cn/Membercenter.aspx");
			HttpResponse res = hc.execute(hg);
			System.out.println(res.getHeaders("Set-Cookie"));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
