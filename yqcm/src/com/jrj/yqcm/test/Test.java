package com.jrj.yqcm.test;

import java.io.File;
import java.io.FileReader;
//import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.jrj.yqcm.TaskOpThread;
import com.jrj.yqcm.TaskQueue;
import com.jrj.yqcm.TaskUtil;
import com.jrj.yqcm.WorldCupThread;
import com.jrj.yqcm.utils.Config;
import com.jrj.yqcm.utils.Constants;
import com.jrj.yqcm.utils.HttpComponentUtils;
import com.jrj.yqcm.utils.OperateUtil;
import com.jrj.yqcm.utils.UrlConstants;
import com.jrj.yqcm.utils.WorldCupUtil;

public class Test {
	public static void main(String[] args) throws Exception {
		List<Properties> list = Config.list;
		int i = 0;
		for (Properties itemProps : list) {
			try {
				// System.out.println(itemProps);
				String name = itemProps.getProperty("name");
				// String cookie = itemProps.getProperty("cookie");
				String cookie = String.valueOf(i);
				String loginUrl = itemProps.getProperty("loginUrl");
				String host = itemProps.getProperty("host");
				String trainIds = itemProps.getProperty("trainIds");
				String wcType = itemProps.getProperty("wcType");
				String wcCaptain = itemProps.getProperty("wcCaptain");
				String wcMatch = itemProps.getProperty("wcMatch");
				String wcCount = itemProps.getProperty("wcCount");
				HttpComponentUtils.login(loginUrl, cookie);
				Map<String, String> params = new HashMap<String, String>();
				String targetId = "1437134";
//				OperateUtil.changeLineip(cookie, host, "1");
//				OperateUtil.test2(cookie, host);
//				OperateUtil.finishUnionTask(cookie, host);
				for (int j = 0; j < 50; j++) {
					String run = OperateUtil.scoutRun(cookie, host);
					System.out.println(j + " run:" + run);
					if (run.indexOf("搜索成功") != -1) {
						String result = OperateUtil.scoutResuolt(cookie, host);
						if (result.indexOf("color:#642100") != -1) {
							System.out.println("result:" + result);
							return;
						} else if (result.indexOf("color:#00db00") != -1) {
							// System.out.println("aabb: buy");
							OperateUtil.buyAndSell(cookie, host, result);
						}
					} else {
						return;
					}
//					Thread.sleep(1000);
				}
//				for (int j = 1000; j <= 1125; j++) {
//					String url = (host
//							+ "/MapMatch/MapMatch.aspx?Type=9&Order=1&Category=" + j);
//					String content = OperateUtil.get(url, cookie);
//					System.out.println("j:" + j + "," + url);
//					System.out.println("content:" + content);
//				}
				// for(int j=0;j<100;j++){
				// TestThread t = new TestThread(cookie, host,targetId);
				// new Thread(t).start();
				// }
				// OperateUtil.test(cookie, host);
				// OperateUtil.exchange("1", cookie, host);
				// OperateUtil.punishSlave(name, cookie, host);
				// OperateUtil.getChiefCupOp(cookie, host);
				// OperateUtil.playPlot(cookie, host);
				// OperateUtil.openGiftBag(cookie, host);
				// OperateUtil.enterBoss(cookie, host);
				// OperateUtil.getGoldShop(cookie, host);
				// if (wcType != null) {
				// params.put("wcType", wcType);
				// }
				// if (wcCaptain != null) {
				// params.put("wcCaptain", wcCaptain);
				// }
				// if (wcCaptain != null) {
				// params.put("wcCaptain", wcCaptain);
				// }
				// if (wcCount != null) {
				// params.put("wcCount", wcCount);
				// }
				// if (wcCount != null) {
				// params.put("wcMatch", wcMatch);
				// }
				// if (wcType != null) {
				// WorldCupThread wcThread = new WorldCupThread(name, cookie,
				// loginUrl, host, params, null);
				// wcThread.start();
				// }
				// HttpComponentUtils.login(loginUrl, cookie);
				// WorldCupUtil.enterWorldCup(cookie, host);
				// String roomId = "42369";
				if ("1".equals(wcType)) {
					// WorldCupUtil.createRoom(cookie, host);
					// WorldCupUtil.startMatch(roomId,cookie, host);
				} else {
					// String roomId = WorldCupUtil.getRoomId(wcCaptain,
					// cookie,
					// host);
					// if(roomId!=null){
					// WorldCupUtil.enterRoom(roomId, cookie, host);
					// }
				}
				// String content = WorldCupUtil.preWorldCup(cookie, host);
				// if ("1".equals(wcMatch) && content.indexOf("现在是小组赛") != -1) {
				// WorldCupUtil.goMatch(cookie, host);
				// } else if ("2".equals(wcMatch)
				// && content.indexOf("现在是淘汰赛") != -1) {
				// WorldCupUtil.goMatch(cookie, host);
				// } else {
				// WorldCupUtil.noMatch(cookie, host);
				// }
				// try {
				// Thread.sleep(60 * 1000);
				// } catch (InterruptedException e1) {
				// e1.printStackTrace();
				// }
				// String url =
				// "http://f9.xba.com.cn/Scene/SceneUpdate.aspx?ID=23";
				// String content = HttpComponentUtils.get(url, cookie);
				// System.out.println(name + ":" + content);
				// TaskQueue tq = new TaskQueue(host, cookie);
				// tq.getTask("1,1,1");
				// tq.getTask("1,1,1");
				// TaskUtil.getTaskAward(cookie, host);
				// int lrt = TaskUtil.getLeftRefreshTime(cookie, host);
				// System.out.println(name + "lrt:" + lrt);
				// while (true) {
				// Task t = tq.poll();
				// System.out.println("t:" + t);
				// if (t == null) {
				// Thread.sleep(60 * 1000);
				// } else {
				// System.out.println("t.getLeftMatchId().size():"
				// + t.getLeftMatchId().size());
				// String url =
				// "http://f9.xba.com.cn/Scene/SceneUpdate.aspx?ID="
				// + t.getSceneId();
				// String content = HttpComponentUtils.get(url, cookie);
				// while (t.getLeftMatchId().size() > 0) {
				// String matchId = t.getLeftMatchId().poll();
				// url = (host + UrlConstants.SIGN_COK).replace("$1",
				// matchId);
				// // System.out.println(url);
				// content = HttpComponentUtils.get(url, cookie);
				// while (content.indexOf("挑战成功") == -1) {
				// System.out.println("content:" + content);
				// Thread.sleep(60 * 1000);
				// content = HttpComponentUtils.get(url, cookie);
				// }
				// System.out.println(name + "," + ",TrainMatchTask,"
				// + new Date() + formatContent(content));
				// }
				// }
				// }
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
	}

	public static Properties getProperties(String file) throws Exception {
		Properties props = new Properties();
		props.load(new FileReader(file));
		return props;
	}

	public static void createFile(String file) throws Exception {
		File f = new File(Constants.DIR + file);
		if (!f.exists()) {
			f.createNewFile();
		}
	}

	public static String formatContent(String content) {
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
