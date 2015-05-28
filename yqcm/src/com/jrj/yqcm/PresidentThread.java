package com.jrj.yqcm;

import java.util.Calendar;

import com.jrj.yqcm.utils.HttpComponentUtils;
import com.jrj.yqcm.utils.OperateUtil;

public class PresidentThread extends Thread {
	private String name;
	private String mainCookie;
	private String loginUrl;
	private String host;

	public PresidentThread(String name, String mainCookie, String loginUrl,
			String host) {
		this.name = name;
		this.mainCookie = mainCookie;
		this.loginUrl = loginUrl;
		this.host = host;
	}

	public void run() {
		while (true) {
			try {
				Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				if (hour == 13) {
					HttpComponentUtils.login(loginUrl, mainCookie);
					System.out.println(name + ",PresidentThread start");
					OperateUtil.president(mainCookie, host, "2");
				} else {
					try {
						Thread.sleep(30 * 1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			try {
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
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
