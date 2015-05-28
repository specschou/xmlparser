package com.jrj.yqcm.task;

import java.util.Date;
import java.util.TimerTask;

import com.jrj.yqcm.utils.HttpComponentUtils;
import com.jrj.yqcm.utils.UrlConstants;

public class TrainMatchTask extends TimerTask {
	private String name;
	private String matchId;
	private String mainCookie;
	private String loginUrl;
	private String host;

	public TrainMatchTask(String name, String matchId, String mainCookie,
			String loginUrl, String host) {
		this.name = name;
		this.matchId = matchId;
		this.mainCookie = mainCookie;
		this.loginUrl = loginUrl;
		this.host = host;
	}

	public void run() {
		try {
			HttpComponentUtils.login(loginUrl, mainCookie);
			String url = (host + UrlConstants.SIGN_COK).replace("$1", matchId);
			// System.out.println(url);
			String content = HttpComponentUtils.get(url, mainCookie);
			System.out.println(name + "," + ",TrainMatchTask," + new Date()
					+ formatContent(content));
//			url = host + UrlConstants.CoinToTool;
			content = HttpComponentUtils.get(url, mainCookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String formatContent(String content) {
		int index = -1;
		int begin = -1;
		int end = -1;
		if ((index = content.indexOf("public_d_process")) != -1) {
			begin = index + 18;
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
