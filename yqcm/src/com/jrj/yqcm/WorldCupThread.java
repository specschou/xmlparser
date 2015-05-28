package com.jrj.yqcm;

import java.util.Calendar;
import java.util.Map;

import com.jrj.yqcm.utils.HttpComponentUtils;
import com.jrj.yqcm.utils.OperateUtil;
import com.jrj.yqcm.utils.WorldCupUtil;

public class WorldCupThread extends Thread {
	private String name;
	private String mainCookie;
	private String loginUrl;
	private String host;
	private String wcrc;
	private String wctid;
	private Map<String, String> params;
	private int gameCount = 0;
	private int totalCount = 0;
	public static String roomId = null;
	public static boolean started = false;

	public WorldCupThread(String name, String mainCookie, String loginUrl,
			String host, String wcrc, String wctid, Map<String, String> params) {
		this.name = name;
		this.mainCookie = mainCookie;
		this.loginUrl = loginUrl;
		this.host = host;
		this.params = params;
		this.wcrc = wcrc;
		this.wctid = wctid;
	}

	public void creatRoom() {
		String content = WorldCupUtil.createRoom(mainCookie, host, wcrc, wctid);
		System.out.println(name + ":createRoom:"
				+ OperateUtil.formatContent(content));
		if (content.indexOf("成功") != -1) {
			System.out.println(name + ":创建成功");
		}
		if (roomId == null) {
			roomId = WorldCupUtil.getRoomId(mainCookie, host);
		}
//		System.out.println("roomId:" + roomId);
		if (roomId != null) {
			for (int i = 0; i < 5; i++) {
				// 尝试5次
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				content = WorldCupUtil.startMatch(roomId, mainCookie, host,
						wcrc);
//				System.out.println(name + ":startMatch:"
//						+ OperateUtil.formatContent(content));
				if (content.indexOf("设定成功") != -1) {
					System.out.println(name + ":开始成功");
					gameCount++;
					started = true;
					break;
				}
			}
		}
	}

	public void enterRoom(String wcCaptain) {
		if (roomId == null) {
			roomId = WorldCupUtil.getRoomId(wcCaptain, mainCookie, host, wcrc);
		}
		if (roomId != null) {
			for (int i = 0; i < 5; i++) {
				// 尝试5次
				String content = WorldCupUtil.enterRoom(roomId, mainCookie,
						host);
//				System.out.println(name + ":enterRoom:"
//						+ OperateUtil.formatContent(content));
				if (content.indexOf("成功") != -1) {
					System.out.println(name + ":加入成功");
					break;
				}
				// 尝试5次
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void xiaozuMatch(String wcMatch) {
		HttpComponentUtils.login(loginUrl, mainCookie);
		String content = "";
		if ("1".equals(wcMatch)) {
//			System.out.println(name + ":xiaozu gomatch");
			content = WorldCupUtil.goMatch(mainCookie, host, wcrc);
			System.out.println(name + ":goMatch:"
					+ OperateUtil.formatContent(content));
		} else {
//			System.out.println(name + ":xiaozu nomatch");
			content = WorldCupUtil.noMatch(mainCookie, host, wcrc);
			System.out.println(name + ":noMatch:"
					+ OperateUtil.formatContent(content));
		}
	}

	public void taotaiMatch(String wcMatch) {
		HttpComponentUtils.login(loginUrl, mainCookie);
		String content = "";
		if ("1".equals(wcMatch)) {
//			System.out.println(name + ":taotai nomatch");
			content = WorldCupUtil.noMatch(mainCookie, host, wcrc);
			System.out.println(name + ":noMatch:"
					+ OperateUtil.formatContent(content));
		} else {
//			System.out.println(name + ":taotai gomatch");
			content = WorldCupUtil.goMatch(mainCookie, host, wcrc);
			System.out.println(name + ":goMatch:"
					+ OperateUtil.formatContent(content));
		}
	}

	public void run() {
		String wcType = params.get("wcType");
		String wcCaptain = params.get("wcCaptain");
		String wcMatch = params.get("wcMatch");
		int wcStartTime = 9;
		int wcEndTime = 12;
		try {
			wcStartTime = Integer.parseInt(params.get("wcStartTime"));
			wcEndTime = Integer.parseInt(params.get("wcEndTime"));
		} catch (Exception e) {
		}
		while (true) {
			try {
				Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int minute = c.get(Calendar.MINUTE);
				if (hour <= wcEndTime && hour >= wcStartTime) {
					String content = "";
					if (hour < wcEndTime) {
						// 在12点前进行
						System.out.println(name + ": worldcup start! "
								+ gameCount);
						// if (gameCount < Integer.parseInt(wcCount)
						// && totalCount < 360) {
						totalCount++;
						HttpComponentUtils.login(loginUrl, mainCookie);
						// if (!nowGame) {
						// 进入
						content = WorldCupUtil.enterWorldCup(mainCookie, host,
								wcrc);
//						System.out.println(name + ":enterWorldCup:"
//								+ OperateUtil.formatContent(content));
						if ("1".equals(wcType)) {
							// 创建队伍
							creatRoom();
						} else {
							// 进入队伍
							enterRoom(wcCaptain);
						}
					}
					// }
					int i = 0;
					do {
							i++;
							content = WorldCupUtil
									.preWorldCup(mainCookie, host);
							// System.out.println(name + ":"
							// + OperateUtil.formatContent(content));
							int showReport = content
									.indexOf("javascript\">ShowReportWithRT(");
//							System.out.println(name + ":ShowReportWithRT:"
//									+ showReport);

							if (content.indexOf("您已经被淘汰") != -1
									|| content.indexOf("您已经获得了世界杯冠军") != -1) {
								started = false;
								String a = WorldCupUtil.exitWorldCup(
										mainCookie, host, wcrc);
								System.out.println(name + ":exitWorldCup:"
										+ OperateUtil.formatContent(a));
								roomId = null;
								break;
							}
							if (showReport == -1) {
								int xiaozu = content.indexOf("现在是小组赛");
								int taotai = content.indexOf("现在是淘汰赛");
								if (xiaozu != -1 || taotai != -1) {
									// started = true;
								}
//								System.out.println(name + ":" + wcMatch + ","
//										+ xiaozu + "," + taotai);
								if ("0".equals(wcMatch)) {
									HttpComponentUtils.login(loginUrl, mainCookie);
//									System.out.println(name + ":nomatch");
									content = WorldCupUtil.noMatch(mainCookie,
											host, wcrc);
									System.out.println(name
											+ ":noMatch:"
											+ OperateUtil
													.formatContent(content));
								} else {
									if (xiaozu != -1) {
										xiaozuMatch(wcMatch);
									} else if (taotai != -1) {
										taotaiMatch(wcMatch);
									}
								}
							} else {
								started = true;
								try {
									Thread.sleep(60 * 1000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							try {
								Thread.sleep(30 * 1000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							// if (!started) {
							// String a = WorldCupUtil.exitWorldCup(mainCookie,
							// host);
							// System.out.println(name + ":"
							// + OperateUtil.formatContent(a));
							// roomId = null;
							// }
					} while (started && i < 150);
					try {
						Thread.sleep(30 * 1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} else {
					// if (toThread != null) {
					// toThread.setStart(true);
					// }
					System.out.println(name + ": worldcup not start! "
							+ gameCount);
					String a = WorldCupUtil
							.exitWorldCup(mainCookie, host, wcrc);
					roomId = null;
					System.out.println(name + ":"
							+ OperateUtil.formatContent(a));
					try {
						Thread.sleep(30 * 60 * 1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
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

	public void reset() {
		gameCount = 0;
		totalCount = 0;
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
