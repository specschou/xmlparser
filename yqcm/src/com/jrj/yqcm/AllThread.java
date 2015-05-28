package com.jrj.yqcm;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import com.jrj.yqcm.Task;
import com.jrj.yqcm.TaskQueue;
import com.jrj.yqcm.TaskUtil;
import com.jrj.yqcm.utils.HttpComponentUtils;
import com.jrj.yqcm.utils.OperateUtil;
import com.jrj.yqcm.utils.UrlConstants;
import com.jrj.yqcm.utils.WorldCupUtil;

public class AllThread extends Thread {
	private String name;
	private String mainCookie;
	private String loginUrl;
	private String host;
	private int hour;
	private int minute;
	private int refreshCount = 0;
	private String wcrc;
	private String roomId;
	public static boolean started = false;
	private Map<String, String> params;

	public AllThread(String name, String mainCookie, String loginUrl,
			String host, Map<String, String> params) {
		this.name = name;
		this.mainCookie = mainCookie;
		this.loginUrl = loginUrl;
		this.host = host;
		this.params = params;
	}

	public void run() {
		System.out.println(name + ",AllThread start.");
		while (true) {
			try {
				long begin = System.currentTimeMillis();

				HttpComponentUtils.login(loginUrl, mainCookie);
				Calendar c = Calendar.getInstance();
				hour = c.get(Calendar.HOUR_OF_DAY);
				minute = c.get(Calendar.MINUTE);
				getGoldShop();
				playPlot();
				openGiftBagAndMore();
				enterBoss();
				enterStadium();
				getStadiumAward();
				specialTrain();
				playerTrain();
				getTicket();
				getGiftBag();
				runTask();
				runWorldCup();

				long end = System.currentTimeMillis();
				long sleep = 30 * 1000;
				if ((end - begin) < 30 * 1000) {
					sleep = 60 * 1000 - (end - begin);
				}
//				System.out.println(name + ",sleep:" + sleep);
				try {
					Thread.sleep(sleep);
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

	public void getGoldShop() {
		if (minute == 1) {
			OperateUtil.getGoldShop(mainCookie, host);
		}
	}

	public void playPlot() {
		if (hour == 7 && (minute == 35 || minute == 45)) {
			OperateUtil.punishSlave(name, mainCookie, host);
			String plotOrder = params.get("plotOrder");
			String plotCategory = params.get("plotCategory");
			if (plotOrder != null && plotCategory != null) {
				OperateUtil.playPlot(mainCookie, host, plotOrder, plotCategory);
			}
		}
	}

	public void openGiftBagAndMore() {
		if (hour == 8 && (minute == 8 || minute == 18)) {
			OperateUtil.getChiefCupOp(mainCookie, host);
			OperateUtil.openGiftBag(mainCookie, host);
			OperateUtil.openBossBag(mainCookie, host);
			if ("specschou1".equals(name)) {
				OperateUtil.exchange("10", mainCookie, host);
			}
		}
	}

	public void enterBoss() {
		if ("1".equals(params.get("boss"))) {
			if (hour == 20 && minute == 0) {
				// OperateUtil.exchange("3", mainCookie, host);
				OperateUtil.enterBoss(mainCookie, host);
			} else if (hour == 20 && minute <= 5) {
				OperateUtil.enterBoss(mainCookie, host);
			}
		}
	}

	public void enterStadium() {
		if ("1".equals(params.get("stadium"))) {
			if (hour == 15
					&& ((minute >= 0 && minute <= 3) || (minute >= 30 && minute <= 33))) {
				OperateUtil.enterStadium(mainCookie, host);
			}
		}
	}

	public void getStadiumAward() {
		if (hour == 16 && (minute == 10 || minute == 15)) {
			OperateUtil.getStadiumAward(mainCookie, host);
		}
	}

	public void specialTrain() {
		if (params.get("trainIds") != null) {
			if (minute % 10 == 0) {
				// System.out.println(name + ":trainIds:"
				// + params.get("trainIds"));
				OperateUtil.specialTrain(name, params.get("trainIds"),
						mainCookie, host);
			}
		}
	}

	public void playerTrain() {
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
		if (hour >= start && hour <= end) {
			// System.out.println(name + ": train start! " + count);
			String id = getPlayerId(playerId);
			String content = OperateUtil.playerTrain(id, mainCookie, host);
			if (content.indexOf("冷却时间") == -1 && content.indexOf("程序错误") == -1) {
				System.out.println(name + "," + ",PlayerTrainTask,"
						+ new Date() + OperateUtil.formatContent(content));
			}
		}
	}

	public void getTicket() {
		if (minute % 10 == 0) {
			OperateUtil.getTicket(mainCookie, host);
		}
	}

	public void getGiftBag() {
		if (minute % 10 == 0) {
			String activeBag = params.get("activeBag");
			if ("1".equals(activeBag)) {
				String a = OperateUtil.getGiftBag(mainCookie, host);
				if (a != null) {
					System.out.println(name + ":"
							+ OperateUtil.formatContent(a));
					if (a.indexOf("您今日还未挑战竞技场") != -1) {
						OperateUtil.cancelActiveBag(mainCookie, host, "81");
					}
				}
			}
		}
	}

	public void runTask() {
		try {
			TaskQueue taskQueue = new TaskQueue(host, mainCookie, name);
			String trainTime = params.get("trainTime");
			String taskType = params.get("taskType");
			int lrt = Integer.parseInt(params.get("lrt"));
			Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			if (hour == 7 && minute <= 10) {
				reset();
			}
			int start = 13;
			if (trainTime != null) {
				try {
					String[] a = trainTime.split("-");
					start = Integer.parseInt(a[1]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (hour > start || hour < 7) {
				System.out.println(name + ": tast start! " + refreshCount);
				taskQueue.getTask(taskType);
				System.out.println(name + ",queue:" + taskQueue.size() + ","
						+ taskQueue);
				if (taskQueue.empty()) {
					int[] taskInfo = TaskUtil.getLeftRefreshTime(mainCookie,
							host);
					if (taskInfo[0] == 0) {
					} else {
						int leftRefreshTime = taskInfo[1];
						System.out.println(name + ":" + "leftRefreshTime:"
								+ leftRefreshTime);
						if (leftRefreshTime > lrt && refreshCount < 5) {
							refreshCount++;
							System.out.println(name + ":" + "refreshTask");
							TaskUtil.refreshTask(mainCookie, host);
						}
					}
				} else {
					Task t = taskQueue.peek();
					if (t != null && t.getLeftMatchId().size() == 0) {
						taskQueue.remove();
						t = taskQueue.peek();
					}
					if (t != null) {
						if (t.getLeftMatchId().size() > 0) {
							TaskUtil.updateScene(t.getSceneId(), mainCookie,
									host);
							try {
								String matchId = t.getLeftMatchId().poll();
								String url = (host + UrlConstants.SIGN_COK)
										.replace("$1", matchId);
								String content = HttpComponentUtils.get(url,
										mainCookie);
								System.out.println(name + ","
										+ ",TrainMatchTask," + new Date()
										+ formatContent(content));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
				TaskUtil.getTaskAward(mainCookie, host);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runWorldCup() {
		String wcType = params.get("wcType");
		String wcCaptain = params.get("wcCaptain");
		String wcMatch = params.get("wcMatch");
		wcrc = params.get("wcrc");
		int wcStartTime = 9;
		int wcEndTime = 12;
		try {
			wcStartTime = Integer.parseInt(params.get("wcStartTime"));
			wcEndTime = Integer.parseInt(params.get("wcEndTime"));
		} catch (Exception e) {
		}
		try {
			Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			if (hour <= wcEndTime && hour >= wcStartTime) {
				String content = "";
				if (hour < wcEndTime) {
					content = WorldCupUtil
							.enterWorldCup(mainCookie, host, wcrc);
					if ("1".equals(wcType)) {
						// 创建队伍
						creatRoom();
					} else {
						// 进入队伍
						enterRoom(wcCaptain);
					}
				}
				content = WorldCupUtil.preWorldCup(mainCookie, host);
				int showReport = content
						.indexOf("javascript\">ShowReportWithRT(");

				if (content.indexOf("您已经被淘汰") != -1
						|| content.indexOf("您已经获得了世界杯冠军") != -1) {
					started = false;
					String a = WorldCupUtil
							.exitWorldCup(mainCookie, host, wcrc);
					System.out.println(name + ":exitWorldCup:"
							+ OperateUtil.formatContent(a));
					roomId = null;
					return;
				}
				if (showReport == -1) {
					int xiaozu = content.indexOf("现在是小组赛");
					int taotai = content.indexOf("现在是淘汰赛");
					if (xiaozu != -1 || taotai != -1) {
						// started = true;
					}
					// System.out.println(name + ":" + wcMatch + ","
					// + xiaozu + "," + taotai);
					if ("0".equals(wcMatch)) {
						// System.out.println(name + ":nomatch");
						content = WorldCupUtil.noMatch(mainCookie, host, wcrc);
					} else {
						if (xiaozu != -1) {
							xiaozuMatch(wcMatch);
						} else if (taotai != -1) {
							taotaiMatch(wcMatch);
						}
					}
				} else {
					started = true;
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

	public void reset() {
		refreshCount = 0;
	}

	public void creatRoom() {
		String wctid = params.get("wctid");
		String content = WorldCupUtil.createRoom(mainCookie, host, wcrc, wctid);
		System.out.println(name + ":createRoom:"
				+ OperateUtil.formatContent(content));
		if (content.indexOf("成功") != -1) {
			System.out.println(name + ":创建成功");
		}
		if (roomId == null) {
			roomId = WorldCupUtil.getRoomId(mainCookie, host);
		}
		if (roomId != null) {
			content = WorldCupUtil.startMatch(roomId, mainCookie, host, wcrc);
			if (content.indexOf("设定成功") != -1) {
				started = true;
				System.out.println(name + ":开始成功");
			}
		}
	}

	public void enterRoom(String wcCaptain) {
		if (roomId == null) {
			roomId = WorldCupUtil.getRoomId(wcCaptain, mainCookie, host, wcrc);
		}
		if (roomId != null) {
			String content = WorldCupUtil.enterRoom(roomId, mainCookie, host);
			if (content.indexOf("成功") != -1) {
				System.out.println(name + ":加入成功");
			}
		}
	}

	public void xiaozuMatch(String wcMatch) {
		String content = "";
		if ("1".equals(wcMatch)) {
			// System.out.println(name + ":xiaozu gomatch");
			content = WorldCupUtil.goMatch(mainCookie, host, wcrc);
			System.out.println(name + ":goMatch:"
					+ OperateUtil.formatContent(content));
		} else {
			// System.out.println(name + ":xiaozu nomatch");
			content = WorldCupUtil.noMatch(mainCookie, host, wcrc);
			System.out.println(name + ":noMatch:"
					+ OperateUtil.formatContent(content));
		}
	}

	public void taotaiMatch(String wcMatch) {
		String content = "";
		if ("1".equals(wcMatch)) {
			// System.out.println(name + ":taotai nomatch");
			content = WorldCupUtil.noMatch(mainCookie, host, wcrc);
			System.out.println(name + ":noMatch:"
					+ OperateUtil.formatContent(content));
		} else {
			// System.out.println(name + ":taotai gomatch");
			content = WorldCupUtil.goMatch(mainCookie, host, wcrc);
			System.out.println(name + ":goMatch:"
					+ OperateUtil.formatContent(content));
		}
	}
}
