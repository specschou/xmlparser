package com.jrj.yqcm.task;

import java.util.Date;
import java.util.Map;
import java.util.TimerTask;

import com.jrj.yqcm.utils.HttpComponentUtils;
import com.jrj.yqcm.utils.OperateUtil;
import com.jrj.yqcm.utils.UrlConstants;

public class CommonTask extends TimerTask {
	private String name;
	private String playerId;
	private String mainCookie;
	private String loginUrl;
	private String host;
	private Map<String, String> params;

	public CommonTask(String name, String playerId, String mainCookie,
			String loginUrl, String host, Map<String, String> params) {
		this.name = name;
		this.playerId = playerId;
		this.mainCookie = mainCookie;
		this.loginUrl = loginUrl;
		this.host = host;
		this.params = params;
	}

	public void run() {
		try {
			String ticket = params.get("ticket");
			if ("1".equals(ticket)) {
				HttpComponentUtils.login(loginUrl, mainCookie);
				OperateUtil.getTicket(mainCookie, host);
			}
			String activeBag = params.get("activeBag");
			if ("1".equals(activeBag)) {
				HttpComponentUtils.login(loginUrl, mainCookie);
				String a = OperateUtil.getGiftBag(mainCookie, host);
				if (a != null) {
					System.out.println(name + ":"
							+ OperateUtil.formatContent(a));
					if (a.indexOf("ƒ˙ΩÒ»’ªπŒ¥") != -1) {
						OperateUtil.cancelActiveBag(mainCookie, host, "81");
						OperateUtil.cancelActiveBag(mainCookie, host, "82");
						OperateUtil.cancelActiveBag(mainCookie, host, "83");
					}
					if (a.indexOf("√‚∑——∞’“«Ú–«") != -1) {
						OperateUtil.scoutRun(mainCookie, host);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
