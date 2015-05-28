package com.jrj.yqcm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorldCupUtil {
	public static String exitWorldCup(String cookie, String host, String wcrc) {
		String url = (host + UrlConstants.EXIT_WORLDCUP).replace("$1", wcrc);
		String content = "≥Ã–Ú¥ÌŒÛ";
		content = HttpComponentUtils.get(url, cookie);
		// System.out.println("content:" + OperateUtil.formatContent(content));
		return content;
	}

	public static String preWorldCup(String cookie, String host) {
		String url = host + UrlConstants.PRE_WORLDCUP;
		String content = "≥Ã–Ú¥ÌŒÛ";
		content = HttpComponentUtils.get(url, cookie);
		// System.out.println("content:" + OperateUtil.formatContent(content));
		return content;
	}

	public static String noMatch(String cookie, String host, String wcrc) {
		String url = (host + UrlConstants.NO_MATCH).replace("$1", wcrc);
		String content = "≥Ã–Ú¥ÌŒÛ";
		content = HttpComponentUtils.get(url, cookie);
		// System.out.println("content:" + OperateUtil.formatContent(content));
		return content;
	}

	public static String goMatch(String cookie, String host, String wcrc) {
		String url = (host + UrlConstants.GO_MATCH).replace("$1", wcrc);
		String content = "≥Ã–Ú¥ÌŒÛ";
		content = HttpComponentUtils.get(url, cookie);
		// System.out.println("content:" + OperateUtil.formatContent(content));
		return content;
	}

	public static String startMatch(String roomId, String cookie, String host,
			String wcrc) {
		String content = "≥Ã–Ú¥ÌŒÛ";
		try {
			String url = (host + UrlConstants.START_MATCH)
					.replace("$1", roomId).replace("$2", wcrc);
			content = HttpComponentUtils.get(url, cookie);
		} catch (Exception e) {
		}
		// System.out.println("content:" + OperateUtil.formatContent(content));
		return content;
	}

	public static String enterWorldCup(String cookie, String host, String wcrc) {
		String url = (host + UrlConstants.ENTER_WORLDCUP).replace("$1", wcrc);
		String content = "≥Ã–Ú¥ÌŒÛ";
		content = HttpComponentUtils.get(url, cookie);
		// System.out.println("content:" + OperateUtil.formatContent(content));
		return content;
	}

	public static String createRoom(String cookie, String host, String wcrc,
			String wctid) {
		String url = (host + UrlConstants.CREATE_ROOM).replace("$1", wcrc)
				.replace("$2", wctid);
		String content = "≥Ã–Ú¥ÌŒÛ";
		content = HttpComponentUtils.get(url, cookie);
		// System.out.println("content:" + OperateUtil.formatContent(content));
		return content;
	}

	public static String getRoomId(String cookie, String host) {
		String roomId = null;
		String url = host + UrlConstants.PRE_WORLDCUP;
		String content = "≥Ã–Ú¥ÌŒÛ";
		content = HttpComponentUtils.get(url, cookie);
		// System.out.println("content:" + content);
		Pattern p = Pattern.compile("(SetBeginGameStatus\\()(.*?)(,)");
		Matcher m = p.matcher(content);
		while (m.find()) {
			String s = m.group(2);
			System.out.println(s);
			return s;
		}
		return roomId;
	}

	public static String getRoomId(String captain, String cookie, String host,
			String wcrc) {
		String roomId = null;
		String url = (host + UrlConstants.SHOW_TEAM).replace("$1", wcrc);
		String content = "≥Ã–Ú¥ÌŒÛ";
		content = HttpComponentUtils.get(url, cookie);
		// System.out.println("content:" + content);
		Pattern p = Pattern.compile("(<tr)(.*?)(</tr>)");
		Pattern p1 = Pattern.compile("(JoinRoom)(.*?)(,)(.*?)(,)");
		Matcher m = p.matcher(content);
		while (m.find()) {
			String s = m.group();
			// System.out.println(s);
			// System.out.println("captain:" + captain);
			if (s.indexOf(captain) != -1) {
				Matcher m1 = p1.matcher(s);
				if (m1.find()) {
					// System.out.println(m1.group(4));
					roomId = m1.group(4);
					break;
				}
			}
		}
		return roomId;
	}

	public static String enterRoom(String roomId, String cookie, String host) {
		String content = "≥Ã–Ú¥ÌŒÛ";
		try {
			String url = (host + UrlConstants.ENTER_ROOM).replace("$1", roomId);
			content = HttpComponentUtils.get(url, cookie);
		} catch (Exception e) {
		}
		// System.out.println("url:" + url);
		// System.out.println("content:" + OperateUtil.formatContent(content));
		return content;
	}
}
