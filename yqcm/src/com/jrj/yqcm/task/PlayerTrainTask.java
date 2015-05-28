package com.jrj.yqcm.task;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimerTask;

import com.jrj.yqcm.utils.HttpComponentUtils;
import com.jrj.yqcm.utils.UrlConstants;

public class PlayerTrainTask extends TimerTask {
	private String name;
	private String playerId;
	private String mainCookie;
	private String loginUrl;
	private String host;
	private Integer count = 0;
	private String trainTime;

	public PlayerTrainTask(String name, String playerId, String mainCookie,
			String loginUrl, String host, String trainTime) {
		this.name = name;
		this.playerId = playerId;
		this.mainCookie = mainCookie;
		this.loginUrl = loginUrl;
		this.host = host;
		this.trainTime = trainTime;
	}

	public void run() {
		try {
			Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			if (hour == 7 && minute == 0) {
				count = 0;
			}
			// if (count < trainCount) {
			int start = 7;
			int end = 13;
			if (trainTime != null) {
				try {
					String[] a = trainTime.split("-");
					start = Integer.parseInt(a[0]);
					end = Integer.parseInt(a[1]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (hour >= start && hour <= end) {
//				System.out.println(name + ": train start! " + count);
				HttpComponentUtils.login(loginUrl, mainCookie);
				String id = getPlayerId(playerId);
				String url = (host + UrlConstants.MARKET_TRANINER).replace(
						"$1", "6").replace("$2", id);
				// System.out.println(url);
				String content = HttpComponentUtils.get(url, mainCookie);
				if (content.indexOf("ÀäÈ´Ê±¼ä") == -1
						&& content.indexOf("³ÌÐò´íÎó") == -1) {
					count++;
					System.out.println(name + "," + ",PlayerTrainTask,"
							+ new Date() + formatContent(content));
				}
			} else {
				System.out.println(name + ": train not start! " + count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPlayerId(String playerId) {
		String id = playerId;
		String[] ids = playerId.split(",");
		int len = ids.length;
		if (len > 1) {
			Random r = new Random();
			int i = r.nextInt(len);
			if (ids[i] != null && ids[i].length() > 5) {
				id = ids[i];
			}
		}
		return id;
	}

	public String formatContent(String content) {
		int index = -1;
		int begin = -1;
		int end = -1;
		if ((index = content.indexOf("padding-bottom:5px")) != -1) {
			begin = index + 21;
		} else if ((index = content.indexOf("public_d_content")) != -1) {
			begin = index + 18;
		}
		end = content.indexOf("</div>", index);
		if (begin != -1 && end != -1) {
			content = content.substring(begin, end);
		}
		return content;
	}
}
