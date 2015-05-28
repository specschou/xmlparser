package com.jrj.yqcm;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.jrj.yqcm.Task;
import com.jrj.yqcm.TaskQueue;
import com.jrj.yqcm.TaskUtil;
import com.jrj.yqcm.utils.HttpComponentUtils;
import com.jrj.yqcm.utils.OperateUtil;
import com.jrj.yqcm.utils.UrlConstants;

public class TimeThread extends Thread {
	private String name;
	private String mainCookie;
	private String loginUrl;
	private String host;
	private Map<String, String> params;

	public TimeThread(String name, String mainCookie, String loginUrl,
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
				// System.out.println(name + ":TimeThread begin:" + new Date());
				// if (params == null || params.size() == 0) {
				// break;
				// }
				HttpComponentUtils.login(loginUrl, mainCookie);

				// OperateUtil.president(mainCookie, host, "1");

				Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int minute = c.get(Calendar.MINUTE);
				if (minute == 1) {
					if (hour % 2 == 1) {
						OperateUtil.getGoldShop(mainCookie, host);
					} else {
						OperateUtil.getGoldShop(mainCookie, host, "2");
					}
				}
				if (hour == 0 && (minute == 5 || minute == 10)) {
					HttpComponentUtils.login(loginUrl, mainCookie, true);
				}
				if (hour == 7 && (minute == 13)) {
					String yearMatch = params.get("yearMatch");
					if ("1".equals(yearMatch)) {
//						OperateUtil.yearMatch(mainCookie, host);
					}
				}
				if (minute == 26 || minute == 56) {
					String trainMatch = params.get("trainMatch");
					if ("1".equals(trainMatch)) {
						 OperateUtil.trainMatch(mainCookie, host);
					}
				}
				if (hour == 7 && (minute == 35 || minute == 45)) {
					OperateUtil.punishSlave(name, mainCookie, host);
					String plotOrder = params.get("plotOrder");
					String plotCategory = params.get("plotCategory");
					if (plotOrder != null && plotCategory != null) {
//						OperateUtil.playPlot(mainCookie, host, plotOrder,
//								plotCategory);
					}
				}
				if (hour == 8 && (minute == 8 || minute == 18)) {
					OperateUtil.getChiefCupOp(mainCookie, host);
					OperateUtil.openGiftBag(mainCookie, host);
					// OperateUtil.openBossBag(mainCookie, host);
				}
				if (hour == 9
						&& (minute == 8 || minute == 18 || minute == 28 || minute == 38)) {
					if ("specschou1".equals(name)) {
						 OperateUtil.exchange("12", mainCookie, host);
					}
				}
				String npCokTime = params.get("npCokTime");
				int start = 100;
				int end = 100;
				if (npCokTime != null) {
					try {
						String[] a = npCokTime.split("-");
						start = Integer.parseInt(a[0]);
						end = Integer.parseInt(a[1]);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (hour >= start && hour < end) {
					OperateUtil.npCokNew(mainCookie, host);
				}
				if (hour == 10 && (minute == 14 || minute == 19)) {
					if ("specschou2-34".equals(name)) {
						OperateUtil.stadiumSign(mainCookie, host);
					}
				}
				if (hour == 12 && (minute == 38 || minute == 48)) {
					OperateUtil.punishSlave(name, mainCookie, host);
					OperateUtil.getChiefCupOp(mainCookie, host);
					// OperateUtil.openGiftBag(mainCookie, host);
					// OperateUtil.openBossBag(mainCookie, host);
				}
				if ("1".equals(params.get("boss"))) {
					// if (hour == 20 && minute == 7) {
					// // OperateUtil.exchange("3", mainCookie, host);
					// OperateUtil.openBossBag(mainCookie, host);
					// OperateUtil.enterBoss(mainCookie, host);
					// } else if (hour == 20 && minute <= 10) {
					// OperateUtil.enterBoss(mainCookie, host);
					// }
					if (hour == 20 && minute <= 10) {
						OperateUtil.openBossBag(mainCookie, host);
						OperateUtil.enterBoss(mainCookie, host);
					}
				}
				if ("1".equals(params.get("stadium"))) {
					if (hour == 15
							&& ((minute >= 0 && minute <= 10) || (minute >= 30 && minute <= 40))) {
						if ("specschou1".equals(name)) {
							OperateUtil.changeLineip(mainCookie, host, "1");
						}
						OperateUtil.enterStadium(mainCookie, host);
					}
				}
				if (hour == 16 && (minute == 10 || minute == 15)) {
					OperateUtil.getStadiumAward(mainCookie, host);
				}
				if (params.get("trainIds") != null) {
					if (minute % 10 == 0) {
						// System.out.println(name + ":trainIds:"
						// + params.get("trainIds"));
						OperateUtil.specialTrain(name, params.get("trainIds"),
								mainCookie, host);
					}
				}
				if (params.get("unionSL") != null) {
					try {
						System.out.println(name + ": unionSL");
						int union_op = Integer.parseInt(params.get("unionSL"));
						OperateUtil.union_op(mainCookie, host, union_op);
					} catch (Exception ignore) {
						System.out.println(name + ": unionSL error");
					}
				}
				if (minute == 3 || minute == 4) {
					OperateUtil.unionTask(mainCookie, host);
					OperateUtil.dayTask(mainCookie, host);
				}
				if (minute == 42 || minute == 47 || minute == 52
						|| minute == 57) {
					String fut = OperateUtil.freshUnionTask(mainCookie, host);
					System.out.println(name + ":freshUnionTask:" + fut);
					OperateUtil.finishUnionTask(mainCookie, host);
					OperateUtil.freshDayTask(mainCookie, host);
					OperateUtil.finishDayTask(mainCookie, host);
				}
				if (minute == 27) {
					OperateUtil.getActiveTask(mainCookie, host);
				}
				// String fut = OperateUtil.freshUnionTask(mainCookie, host);
				// System.out.println(name + ":freshUnionTask:" + fut);
				// OperateUtil.finishUnionTask(mainCookie, host);
				// System.out.println(name + ":TimeThread end:" + new Date());
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
