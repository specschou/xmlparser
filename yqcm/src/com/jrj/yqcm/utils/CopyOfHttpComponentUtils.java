package com.jrj.yqcm.utils;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

public class CopyOfHttpComponentUtils {
	private static HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
	private static HttpConnectionClient httpConnectionClient = new HttpConnectionClient();
	private static ThreadSafeClientConnManager cm;
	private static HttpClient httpclient;
//	static {
//		// HttpConnectionClient≥ı ºªØ
//		try {
//			httpConnectionClient.setCodeing("utf-8");
//			httpConnectionClient.setConnectionMaxPerHost(100);
//			httpConnectionClient.setConnectionMaxTotal(300);
//			httpConnectionClient.setConnectionTimeOut(10000);
//			httpConnectionClient.setSoTimeOut(18000);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	static{

        ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager();
        cm.setMaxTotal(100);

//        HttpClient httpclient = new DefaultHttpClient(cm);
	}

	public static String get(String url, String mainCookie) {
		DefaultHttpParams.getDefaultParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
		GetMethod gm = new GetMethod(url);
		Header[] headers = gm.getRequestHeaders();
		System.out.println("header1:");
		for(Header h:headers){
			System.out.println(h.getName() + ":::" + h.getValue() + ",");
		}
		System.out.println("header2:");
		gm.setRequestHeader("Cookie", "");
		gm.setRequestHeader("Referer", "http://f9.xba.com.cn/Default.aspx");
		gm.setRequestHeader("Cookie", mainCookie);
		headers = gm.getRequestHeaders();
		for(Header h:headers){
			System.out.println(h.getName() + ":::" + h.getValue() + ",");
		}
		System.out.println("header25:" + mainCookie);
//		String content = httpConnectionClient.getContextByGetMethod(gm);
		try {
			httpClient.executeMethod(gm);
			String content = new String(gm.getResponseBody(), "utf-8");
			headers = gm.getRequestHeaders();
			for(Header h:headers){
				System.out.println(h.getName() + ":" + h.getValue() + ",");
			}
			gm.setRequestHeader("Cookie", "");
			headers = gm.getRequestHeaders();
			System.out.println("header3:");
			for(Header h:headers){
				System.out.println(h.getName() + ":" + h.getValue() + ",");
			}
			System.out.println("header4:");
			return content;
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // always release the connection after we're done 
			gm.removeRequestHeader("Referer");
			gm.removeRequestHeader("Cookie");
            gm.releaseConnection();
        }
		return null;
	}

	public static void login(String url) {
		DefaultHttpParams.getDefaultParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
		GetMethod gm = new GetMethod(url);
		gm.setRequestHeader("Referer", url);
//		httpConnectionClient.getContextByGetMethod(gm);
		try {
			httpClient.executeMethod(gm);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // always release the connection after we're done 
			gm.removeRequestHeader("Referer");
            gm.releaseConnection();
        }
	}

	public static String get1(String url, String mainCookie) {
		DefaultHttpParams.getDefaultParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
		GetMethod gm = new GetMethod(url);
		Header[] headers = gm.getRequestHeaders();
		System.out.println("header1:");
		for(Header h:headers){
			System.out.println(h.getName() + ":::" + h.getValue() + ",");
		}
		System.out.println("header2:");
		gm.setRequestHeader("Cookie", "");
		gm.setRequestHeader("Referer", "http://f9.xba.com.cn/Default.aspx");
		gm.setRequestHeader("Cookie", mainCookie);
		headers = gm.getRequestHeaders();
		for(Header h:headers){
			System.out.println(h.getName() + ":::" + h.getValue() + ",");
		}
		System.out.println("header25:" + mainCookie);
//		String content = httpConnectionClient.getContextByGetMethod(gm);
		try {
			httpClient.executeMethod(gm);
			String content = new String(gm.getResponseBody(), "utf-8");
			headers = gm.getRequestHeaders();
			for(Header h:headers){
				System.out.println(h.getName() + ":" + h.getValue() + ",");
			}
			gm.setRequestHeader("Cookie", "");
			headers = gm.getRequestHeaders();
			System.out.println("header3:");
			for(Header h:headers){
				System.out.println(h.getName() + ":" + h.getValue() + ",");
			}
			System.out.println("header4:");
			return content;
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // always release the connection after we're done 
			gm.removeRequestHeader("Referer");
			gm.removeRequestHeader("Cookie");
            gm.releaseConnection();
        }
		return null;
	}

	public static void login1(String url) {
		DefaultHttpParams.getDefaultParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
		GetMethod gm = new GetMethod(url);
		gm.setRequestHeader("Referer", url);
//		httpConnectionClient.getContextByGetMethod(gm);
		try {
			httpClient.executeMethod(gm);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // always release the connection after we're done 
			gm.removeRequestHeader("Referer");
            gm.releaseConnection();
        }
	}
}
