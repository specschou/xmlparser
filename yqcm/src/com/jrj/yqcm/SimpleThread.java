package com.jrj.yqcm;

import java.util.Calendar;
import java.util.Map;
import java.util.Random;

import com.jrj.yqcm.utils.HttpComponentUtils;
import com.jrj.yqcm.utils.OperateUtil;
import com.jrj.yqcm.utils.UrlConstants;

public class SimpleThread extends Thread {
	private String name;
	private String mainCookie;
	private String loginUrl;
	private String host;
	private Map<String, String> params;

	public SimpleThread(String name, String mainCookie, String loginUrl,
			String host, Map<String, String> params) {
		this.name = name;
		this.mainCookie = mainCookie;
		this.loginUrl = loginUrl;
		this.host = host;
		this.params = params;
	}

	public void run() {
		while (true) {
			try {
				HttpComponentUtils.login(loginUrl, mainCookie);
				Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int minute = c.get(Calendar.MINUTE);
				if (minute == 1) {
					OperateUtil.getGoldShop(mainCookie, host);
				}
				if (hour == 7 && (minute == 35 || minute == 45)) {
					OperateUtil.punishSlave(name, mainCookie, host);
					String plotOrder = params.get("plotOrder");
					String plotCategory = params.get("plotCategory");
					if (plotOrder != null && plotCategory != null) {
						OperateUtil.playPlot(mainCookie, host, plotOrder,
								plotCategory);
					}
				}
				if (hour == 8 && (minute == 8 || minute == 18)) {
					OperateUtil.getChiefCupOp(mainCookie, host);
					OperateUtil.openGiftBag(mainCookie, host);
					OperateUtil.openBossBag(mainCookie, host);
					if ("specschou1".equals(name)) {
						OperateUtil.exchange("10", mainCookie, host);
					}
				}
				if ("1".equals(params.get("boss"))) {
					if (hour == 20 && minute == 0) {
						// OperateUtil.exchange("3", mainCookie, host);
						OperateUtil.enterBoss(mainCookie, host);
					} else if (hour == 20 && minute <= 5) {
						OperateUtil.enterBoss(mainCookie, host);
					}
				}
				if ("1".equals(params.get("stadium"))) {
					if (hour == 15
							&& ((minute >= 0 && minute <= 3) || (minute >= 30 && minute <= 33))) {
						OperateUtil.enterStadium(mainCookie, host);
					}
				}
				if (hour == 16 && (minute == 10 || minute == 15)) {
					OperateUtil.getStadiumAward(mainCookie, host);
				}
				if (params.get("trainIds") != null) {
					if (minute == 0) {
						OperateUtil.specialTrain(name, params.get("trainIds"),
								mainCookie, host);
					}
				}
				String ticket = params.get("ticket");
				if ("1".equals(ticket)) {
					if (minute % 10 == 0) {
						OperateUtil.getTicket(mainCookie, host);
					}
				}
				String activeBag = params.get("activeBag");
				if ("1".equals(activeBag)) {
					if (minute % 10 == 0) {
						String a = OperateUtil.getGiftBag(mainCookie, host);
						if (a != null) {
							if (a.indexOf("您今日还未挑战竞技场") != -1) {
								OperateUtil.cancelActiveBag(mainCookie, host, "81");
							}
						}
					}
				}
				int start = 7;
				int end = 13;
				String trainTime = params.get("trainTime");
				String playerId = params.get("playerId");
				if (trainTime != null) {
					try {
						String[] a = trainTime.split("-");
						start = Integer.parseInt(a[0]);
						end = Integer.parseInt(a[1]);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (hour >= start && hour <= end && minute % 5 == 0) {
					String id = getPlayerId(playerId);
					String url = (host + UrlConstants.MARKET_TRANINER).replace(
							"$1", "6").replace("$2", id);
					HttpComponentUtils.get(url, mainCookie);
				}
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
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
